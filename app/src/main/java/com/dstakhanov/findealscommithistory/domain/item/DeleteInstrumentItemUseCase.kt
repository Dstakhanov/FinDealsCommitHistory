package com.dstakhanov.findealscommithistory.domain.item


class DeleteInstrumentItemUseCase constructor(
    private val instrumentListRepository: InstrumentListRepository
) {
    suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListRepository.deleteInstrumentItem(instrumentItem)
    }
}