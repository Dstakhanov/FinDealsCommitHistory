package com.dstakhanov.findealscommithistory.presentation.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstakhanov.findealscommithistory.domain.item.DeleteInstrumentItemUseCase
import com.dstakhanov.findealscommithistory.domain.item.EditInstrumentItemUseCase
import com.dstakhanov.findealscommithistory.domain.item.GetInstrumentListUseCase
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstrumentItemMainViewModel @Inject constructor(
    private val getInstrumentListUseCase: GetInstrumentListUseCase,
    private val deleteInstrumentItemUseCase: DeleteInstrumentItemUseCase,
    private val editInstrumentItemUseCase: EditInstrumentItemUseCase

) : ViewModel() {

    val instrumentList = getInstrumentListUseCase.getInstrumentList()

    fun deleteInstrumentItem(instrumentItem: InstrumentItem) {
        viewModelScope.launch {
            deleteInstrumentItemUseCase.deleteInstrumentItem(instrumentItem)
        }

    }

    fun changeEnableState(instrumentItem: InstrumentItem) {
        viewModelScope.launch {
            val newItem = instrumentItem.copy(enabled = !instrumentItem.enabled)
            editInstrumentItemUseCase.editInstrumentItem(newItem)
        }
    }
}