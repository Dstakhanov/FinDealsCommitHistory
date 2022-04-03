package com.dstakhanov.item.domain

import javax.inject.Inject


class EditInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun editInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.editInstrumentItem(instrumentItem)
    }
}