package com.judedevs.translatorapp.translate.data.history

import com.judedevs.translatorapp.core.domain.util.CommonFlow
import com.judedevs.translatorapp.core.domain.util.toCommonFlow
import com.judedevs.translatorapp.database.TranslateDatabase
import com.judedevs.translatorapp.translate.domain.history.HistoryDataSource
import com.judedevs.translatorapp.translate.domain.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(db: TranslateDatabase): HistoryDataSource {
    private val queries = db.translateQueries
    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { historyList ->
                historyList.map { historyEntity ->
                    historyEntity.toHistoryItem()
                }
            }
            .toCommonFlow()
    }

    override suspend fun insertHistoryItem(historyItem: HistoryItem) {
        queries.insertHistory(
            id = historyItem.id,
            fromLanguageCode = historyItem.fromLanguageCode,
            fromText = historyItem.fromText,
            toLanguageCode = historyItem.toLanguageCode,
            toText = historyItem.toText,
            timeStamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}