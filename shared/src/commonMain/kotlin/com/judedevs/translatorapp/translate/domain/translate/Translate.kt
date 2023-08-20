package com.judedevs.translatorapp.translate.domain.translate

import com.judedevs.translatorapp.core.domain.language.Language
import com.judedevs.translatorapp.core.domain.util.Resource
import com.judedevs.translatorapp.translate.domain.history.HistoryDataSource
import com.judedevs.translatorapp.translate.domain.history.HistoryItem

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {
    suspend fun execute(
        fromText: String,
        fromLanguage: Language,
        toLanguage: Language): Resource<String> {
        return try {
            val translatedText = client.translate(fromLanguage, fromText, toLanguage)

            historyDataSource.insertHistoryItem(HistoryItem(
                id = null,
                fromLanguageCode = fromLanguage.langCode,
                fromText = fromText,
                toLanguageCode = toLanguage.langCode,
                toText = translatedText
            ))

            Resource.Success(translatedText)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}