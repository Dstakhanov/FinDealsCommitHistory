package com.dstakhanov.item.domain

import javax.inject.Inject


class DeleteInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.deleteInstrumentItem(instrumentItem)
    }
}