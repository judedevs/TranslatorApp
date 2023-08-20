package com.judedevs.translatorapp.translate.presentation

import com.judedevs.translatorapp.core.presentation.UILanguage

sealed class TranslateEvent {
    data class ChooseFromLanguage(val language: UILanguage) : TranslateEvent()
    data class ChooseToLanguage(val language: UILanguage) : TranslateEvent()
    object StopChoosingLanguage: TranslateEvent()
    object SwapLanguages: TranslateEvent()
    data class ChangeTranslationText(val text: String) : TranslateEvent()
    object Translate : TranslateEvent()
    object OpenFromLanguageDropDown: TranslateEvent()
    object OpenToLanguageDropDown: TranslateEvent()
    object CloseTranslation: TranslateEvent()
    data class SelectHistoryItem(val historyItem: UIHistoryItem) : TranslateEvent()
    object EditTranslation: TranslateEvent()
    object RecordAudio: TranslateEvent()
    data class SubmitVoiceResult(val result: String?) : TranslateEvent()
    object OnErrorSeen: TranslateEvent()
}
