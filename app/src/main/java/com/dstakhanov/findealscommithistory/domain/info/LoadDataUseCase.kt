package com.dstakhanov.findealscommithistory.domain.info

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    suspend operator fun invoke() = instrumentInfoRepository.loadData()
}