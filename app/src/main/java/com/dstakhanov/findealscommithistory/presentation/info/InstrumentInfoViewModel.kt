package com.dstakhanov.findealscommithistory.presentation.info

import androidx.lifecycle.ViewModel
import com.dstakhanov.findealscommithistory.domain.info.GetInstrumentInfoListUseCase
import com.dstakhanov.findealscommithistory.domain.info.GetInstrumentInfoUseCase
import com.dstakhanov.findealscommithistory.domain.info.LoadDataUseCase

class InstrumentInfoViewModel constructor(
    private val getInstrumentInfoListUseCase: GetInstrumentInfoListUseCase,
    private val getInstrumentInfoUseCase: GetInstrumentInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

    val instrumentInfoList = getInstrumentInfoListUseCase()

    fun getDetailInfo(fSym: String) = getInstrumentInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}