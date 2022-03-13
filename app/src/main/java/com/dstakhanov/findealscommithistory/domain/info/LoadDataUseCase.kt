package com.dstakhanov.findealscommithistory.domain.info

class LoadDataUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.loadData()
}