package com.dstakhanov.findealscommithistory.domain

import androidx.lifecycle.LiveData

interface InstrumentItemRepository {

    suspend fun addInstrumentItem(instrumentItem: InstrumentItem)

    suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem)

    suspend fun editInstrumentItem(instrumentItem: InstrumentItem)

    fun getInstrumentList(): LiveData<List<InstrumentItem>>

    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem
}