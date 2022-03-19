package com.dstakhanov.findealscommithistory.domain.info

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.loadData()
}