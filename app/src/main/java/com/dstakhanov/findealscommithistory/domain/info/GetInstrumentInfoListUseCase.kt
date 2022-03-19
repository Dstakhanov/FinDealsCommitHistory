package com.dstakhanov.findealscommithistory.domain.info

import javax.inject.Inject

class GetInstrumentInfoListUseCase @Inject constructor(
    private val instrumentInfoRepository: InstrumentInfoRepository
) {
    operator fun invoke() = instrumentInfoRepository.getInstrumentInfoList()
}