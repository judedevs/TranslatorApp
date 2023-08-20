package com.judedevs.translatorapp.translate.presentation

import com.judedevs.translatorapp.core.presentation.UILanguage

data class UIHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UILanguage,
    val toLanguage: UILanguage
)
