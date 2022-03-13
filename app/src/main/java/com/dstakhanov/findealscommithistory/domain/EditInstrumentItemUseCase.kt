package com.dstakhanov.findealscommithistory.domain


class EditInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun editInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.editInstrumentItem(instrumentItem)
    }
}