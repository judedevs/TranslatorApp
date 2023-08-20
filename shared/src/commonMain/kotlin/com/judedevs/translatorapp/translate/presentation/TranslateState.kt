package com.judedevs.translatorapp.translate.presentation

import com.judedevs.translatorapp.core.presentation.UILanguage
import com.judedevs.translatorapp.translate.domain.translate.TranslateError

data class TranslateState(
    val fromText: String = "",
    val toText: String? = null,
    val isTranslating: Boolean = false,
    val fromLanguage: UILanguage = UILanguage.byCode("en"),
    val toLanguage: UILanguage = UILanguage.byCode("de"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: TranslateError? = null,
    val history: List<UIHistoryItem> = emptyList()
)
