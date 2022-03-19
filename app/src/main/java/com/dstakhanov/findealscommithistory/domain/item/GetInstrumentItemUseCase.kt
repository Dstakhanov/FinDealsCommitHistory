package com.dstakhanov.findealscommithistory.domain.item

import javax.inject.Inject


class GetInstrumentItemUseCase @Inject constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem {
        return instrumentListRepository.getInstrumentItem(instrumentItemId)
    }
}