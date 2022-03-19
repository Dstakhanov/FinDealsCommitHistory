package com.dstakhanov.findealscommithistory.domain.info

import androidx.lifecycle.LiveData

interface InstrumentInfoRepository {

    fun getInstrumentInfoList(): LiveData<List<InstrumentInfo>>

    fun getInstrumentInfo(fromSymbol: String): LiveData<InstrumentInfo>

    suspend fun loadData()

}