package com.dstakhanov.findealscommithistory.domain.item

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetInstrumentListUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    fun getInstrumentList(): LiveData<List<InstrumentItem>> {
        return instrumentListRepository.getInstrumentList()
    }

}