package com.dstakhanov.findealscommithistory.domain.info

class GetInstrumentInfoUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke(fromSymbol: String) =
        instrumentInfoRepository.getInstrumentInfo(fromSymbol)
}