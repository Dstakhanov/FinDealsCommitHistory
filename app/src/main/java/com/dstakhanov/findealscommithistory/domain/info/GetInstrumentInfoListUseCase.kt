package com.dstakhanov.findealscommithistory.domain.info

class GetInstrumentInfoListUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.getInstrumentInfoList()
}