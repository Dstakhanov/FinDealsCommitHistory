package com.dstakhanov.item.domain

import javax.inject.Inject


class GetInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem {
        return instrumentListRepository.getInstrumentItem(instrumentItemId)
    }
}