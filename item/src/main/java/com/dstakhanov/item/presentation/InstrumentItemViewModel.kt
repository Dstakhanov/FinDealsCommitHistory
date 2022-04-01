package com.dstakhanov.item.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstrumentItemViewModel @Inject constructor(
    private val getInstrumentListUseCase: com.dstakhanov.item.domain.GetInstrumentListUseCase,
    private val deleteInstrumentItemUseCase: com.dstakhanov.item.domain.DeleteInstrumentItemUseCase,
    private val editInstrumentItemUseCase: com.dstakhanov.item.domain.EditInstrumentItemUseCase

) : ViewModel() {

    val instrumentList = getInstrumentListUseCase.getInstrumentList()

    fun deleteInstrumentItem(instrumentItem: com.dstakhanov.item.domain.InstrumentItem) {
        viewModelScope.launch {
            deleteInstrumentItemUseCase.deleteInstrumentItem(instrumentItem)
        }

    }

    fun changeEnableState(instrumentItem: com.dstakhanov.item.domain.InstrumentItem) {
        viewModelScope.launch {
            val newItem = instrumentItem.copy(enabled = !instrumentItem.enabled)
            editInstrumentItemUseCase.editInstrumentItem(newItem)
        }
    }
}