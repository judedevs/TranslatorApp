package com.judedevs.translatorapp.core.presentation

import com.judedevs.translatorapp.core.domain.language.Language

expect class UILanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UILanguage
        val allLanguages: List<UILanguage>
    }
}