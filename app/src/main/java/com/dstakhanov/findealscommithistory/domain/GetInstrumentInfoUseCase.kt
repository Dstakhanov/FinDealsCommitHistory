package com.dstakhanov.findealscommithistory.domain

class GetInstrumentInfoUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke(fromSymbol: String) =
        instrumentInfoRepository.getInstrumentInfo(fromSymbol)
}