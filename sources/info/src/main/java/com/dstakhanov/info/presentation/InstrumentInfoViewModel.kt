package com.dstakhanov.info.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class InstrumentInfoViewModel @Inject constructor(
    private val getInstrumentInfoListUseCase: com.dstakhanov.info.domain.GetInstrumentInfoListUseCase,
    private val getInstrumentInfoUseCase: com.dstakhanov.info.domain.GetInstrumentInfoUseCase,
    private val loadDataUseCase: com.dstakhanov.info.domain.LoadDataUseCase,
) : ViewModel() {

    val instrumentInfoList = getInstrumentInfoListUseCase()

    fun getDetailInfo(fSym: String) = getInstrumentInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}