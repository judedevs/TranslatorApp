package com.judedevs.translatorapp.translate.domain.history

import com.judedevs.translatorapp.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(historyItem: HistoryItem)
}