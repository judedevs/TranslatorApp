package com.judedevs.translatorapp.core.presentation

import com.judedevs.translatorapp.core.domain.language.Language

actual class UILanguage(
    actual val language: Language,
    val imageName: String
) {
    actual companion object {
        actual fun byCode(langCode: String): UILanguage {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Language with code $langCode not found")
        }

        actual val allLanguages: List<UILanguage>
            get() = Language.values().map { language ->
                UILanguage(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }
    }
}