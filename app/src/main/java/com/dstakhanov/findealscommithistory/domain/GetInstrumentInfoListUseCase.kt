package com.dstakhanov.findealscommithistory.domain

class GetInstrumentInfoListUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.getInstrumentInfoList()
}