package com.dstakhanov.item.domain

import androidx.lifecycle.LiveData

interface InstrumentListRepository {

    suspend fun addInstrumentItem(instrumentItem: InstrumentItem)

    suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem)

    suspend fun editInstrumentItem(instrumentItem: InstrumentItem)

    fun getInstrumentList(): LiveData<List<InstrumentItem>>

    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem
}