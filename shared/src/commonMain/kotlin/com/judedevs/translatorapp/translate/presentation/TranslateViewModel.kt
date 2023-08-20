package com.judedevs.translatorapp.translate.presentation

import com.judedevs.translatorapp.core.domain.util.Resource
import com.judedevs.translatorapp.core.domain.util.toCommonStateFlow
import com.judedevs.translatorapp.core.presentation.UILanguage
import com.judedevs.translatorapp.translate.domain.history.HistoryDataSource
import com.judedevs.translatorapp.translate.domain.translate.Translate
import com.judedevs.translatorapp.translate.domain.translate.TranslateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource,
    private val coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(TranslateState())
    val state = combine(
        _state,
        historyDataSource.getHistory()
    ) { state, history ->
        if(state.history != history) {
            state.copy(history = history.mapNotNull { UIHistoryItem(
                id = it.id ?: return@mapNotNull null,
                fromText = it.fromText,
                toText = it.toText,
                fromLanguage = UILanguage.byCode(it.fromLanguageCode),
                toLanguage = UILanguage.byCode(it.toLanguageCode)
            ) })
        } else {
            state
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState())
        .toCommonStateFlow()

    private var translateJob: Job? = null

    fun onEvent(event: TranslateEvent) {
        when(event) {
            is TranslateEvent.ChooseFromLanguage -> {
                val newState = _state.updateAndGet {
                    it.copy(
                        isChoosingFromLanguage = false,
                        fromLanguage = event.language
                    )
                }
                translate(newState)
            }
            is TranslateEvent.ChooseToLanguage -> {
                val newState = _state.updateAndGet {
                    it.copy(
                        isChoosingToLanguage = false,
                        toLanguage = event.language
                    )
                }
                translate(newState)
            }
            is TranslateEvent.StopChoosingLanguage -> {
                _state.update {
                    it.copy(isChoosingFromLanguage = false, isChoosingToLanguage = false)
                }
            }
            is TranslateEvent.SwapLanguages -> {
                _state.update {
                    it.copy(
                        fromLanguage = it.toLanguage,
                        toLanguage = it.fromLanguage,
                        fromText = it.toText ?: "",
                        toText = if(it.toText != null) it.fromText else null
                    )
                }
            }
            is TranslateEvent.ChangeTranslationText -> {
                _state.update {
                    it.copy(fromText = event.text)
                }
            }
            is TranslateEvent.Translate -> {
                translate(state.value)
            }
            is TranslateEvent.OpenFromLanguageDropDown -> {
                _state.update { it.copy(isChoosingFromLanguage = true) }
            }
            is TranslateEvent.OpenToLanguageDropDown -> {
                _state.update { it.copy(isChoosingToLanguage = true) }
            }
            is TranslateEvent.CloseTranslation -> {
                _state.update {
                    it.copy(isTranslating = false, fromText = "", toText = null)
                }
            }
            is TranslateEvent.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update { it.copy(
                    fromText = event.historyItem.fromText,
                    toText = event.historyItem.toText,
                    isTranslating = false,
                    fromLanguage = event.historyItem.fromLanguage,
                    toLanguage = event.historyItem.toLanguage
                )
                }
            }
            is TranslateEvent.EditTranslation -> {
                if(state.value.toText != null) {
                    _state.update {
                        it.copy(
                            toText = null,
                            isTranslating = false
                            )
                    }
                }
            }
            is TranslateEvent.RecordAudio -> Unit
            is TranslateEvent.SubmitVoiceResult -> {
                _state.update {
                    it.copy(
                        fromText = event.result ?: it.fromText,
                        isTranslating = if(event.result != null) false else it.isTranslating,
                        toText = if(event.result != null) null else it.toText
                    )
                }
            }
            is TranslateEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(error = null)
                }
            }
        }
    }
    private fun translate(state: TranslateState) {
        if(state.isTranslating || state.fromText.isBlank()) return

        translateJob = viewModelScope.launch {
            _state.update {
                it.copy(isTranslating = true)
            }
            val result = translate.execute(
                fromLanguage = state.fromLanguage.language,
                toLanguage = state.toLanguage.language,
                fromText = state.fromText
            )
            when(result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            toText = result.data,
                            isTranslating = false,
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error =  (result.throwable as? TranslateException)?.error
                        )
                    }
                }
            }
        }
    }
}