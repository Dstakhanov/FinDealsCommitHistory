package com.dstakhanov.findealscommithistory.domain.item


class GetInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem {
        return instrumentListRepository.getInstrumentItem(instrumentItemId)
    }
}