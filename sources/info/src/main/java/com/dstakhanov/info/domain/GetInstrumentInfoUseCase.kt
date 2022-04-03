package com.dstakhanov.info.domain

import javax.inject.Inject

class GetInstrumentInfoUseCase @Inject constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke(fromSymbol: String) =
        instrumentInfoRepository.getInstrumentInfo(fromSymbol)
}