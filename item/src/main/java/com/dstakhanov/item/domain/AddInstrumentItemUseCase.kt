package com.dstakhanov.item.domain

import javax.inject.Inject


class AddInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun addInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.addInstrumentItem(instrumentItem)
    }
}
