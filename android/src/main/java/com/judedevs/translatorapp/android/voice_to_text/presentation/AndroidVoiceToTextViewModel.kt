package com.judedevs.translatorapp.android.voice_to_text.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judedevs.translatorapp.voice_to_text.domain.VoiceToTextParser
import com.judedevs.translatorapp.voice_to_text.presentation.VoiceToTextEvent
import com.judedevs.translatorapp.voice_to_text.presentation.VoiceToTextViewModel
import javax.inject.Inject

class AndroidVoiceToTextViewModel @Inject constructor(
    private val parser: VoiceToTextParser
): ViewModel() {
    private val viewModel by lazy {
        VoiceToTextViewModel(
            parser = parser,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    fun onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event)
    }
}