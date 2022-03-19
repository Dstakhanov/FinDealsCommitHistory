package com.dstakhanov.findealscommithistory.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstakhanov.findealscommithistory.domain.info.GetInstrumentInfoListUseCase
import com.dstakhanov.findealscommithistory.domain.info.GetInstrumentInfoUseCase
import com.dstakhanov.findealscommithistory.domain.info.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstrumentInfoViewModel @Inject constructor(
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