package com.dstakhanov.findealscommithistory.domain

import androidx.lifecycle.LiveData

interface InstrumentInfoRepository {

    fun getInstrumentInfoList(): LiveData<List<InstrumentInfo>>

    fun getInstrumentInfo(fromSymbol: String): LiveData<InstrumentInfo>

    fun loadData()

}