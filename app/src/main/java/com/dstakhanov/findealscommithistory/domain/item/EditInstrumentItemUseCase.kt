package com.dstakhanov.findealscommithistory.domain.item


class EditInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun editInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.editInstrumentItem(instrumentItem)
    }
}