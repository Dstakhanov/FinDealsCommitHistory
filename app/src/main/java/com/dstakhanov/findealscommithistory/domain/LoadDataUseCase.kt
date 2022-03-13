package com.dstakhanov.findealscommithistory.domain

class LoadDataUseCase constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.loadData()
}