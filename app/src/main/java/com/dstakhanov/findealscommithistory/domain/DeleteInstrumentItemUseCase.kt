package com.dstakhanov.findealscommithistory.domain


class DeleteInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.deleteInstrumentItem(instrumentItem)
    }
}