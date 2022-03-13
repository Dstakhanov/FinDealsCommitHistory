package com.dstakhanov.findealscommithistory.domain

import androidx.lifecycle.LiveData

class GetInstrumentListUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    fun getInstrumentList(): LiveData<List<InstrumentItem>> {
        return instrumentListRepository.getInstrumentList()
    }

}