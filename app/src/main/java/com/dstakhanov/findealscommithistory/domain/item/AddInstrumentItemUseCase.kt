package com.dstakhanov.findealscommithistory.domain.item


class AddInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun addInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.addInstrumentItem(instrumentItem)
    }
}
