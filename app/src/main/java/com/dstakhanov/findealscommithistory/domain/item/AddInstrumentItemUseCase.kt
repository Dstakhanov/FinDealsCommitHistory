package com.dstakhanov.findealscommithistory.domain.item

import javax.inject.Inject


class AddInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun addInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.addInstrumentItem(instrumentItem)
    }
}
