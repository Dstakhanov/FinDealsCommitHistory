package com.dstakhanov.findealscommithistory.presentation.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstakhanov.findealscommithistory.domain.item.AddInstrumentItemUseCase
import com.dstakhanov.findealscommithistory.domain.item.EditInstrumentItemUseCase
import com.dstakhanov.findealscommithistory.domain.item.GetInstrumentItemUseCase
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import kotlinx.coroutines.launch

class InstrumentItemViewModel constructor(
    private val getInstrumentItemUseCase: GetInstrumentItemUseCase,
    private val addInstrumentItemUseCase: AddInstrumentItemUseCase,
    private val editInstrumentItemUseCase: EditInstrumentItemUseCase
) : ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _errorInputPrice = MutableLiveData<Boolean>()
    val errorInputPrice: LiveData<Boolean>
        get() = _errorInputPrice

    private val _shopItem = MutableLiveData<InstrumentItem>()
    val shopItem: LiveData<InstrumentItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()

    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getInstrumentItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getInstrumentItemUseCase.getInstrumentItem(shopItemId)
            _shopItem.value = item
        }

    }

    fun addInstrumentItem(inputName: String?, inputCount: String?, inputPrice: String?) {
        val symbol = parseName(inputName)
        val count = parseCount(inputCount)
        val price = parsePrice(inputPrice)
        val createDate = System.currentTimeMillis()
        val fieldsValid = validateInput(symbol, count, price)
        if (fieldsValid) {
            viewModelScope.launch {
                val shopItem = InstrumentItem(symbol, count, price, createDate, true)
                addInstrumentItemUseCase.addInstrumentItem(shopItem)
                finishWork()
            }
        }

    }

    fun editInstrumentItem(
        inputName: String?,
        inputCount: String?,
        inputPrice: String?,
        inputDate: String?
    ) {
        val symbol = parseName(inputName)
        val count = parseCount(inputCount)
        val price = parsePrice(inputPrice)
        val createDate = parseDate(inputDate)
        val fieldsValid = validateInput(symbol, count, price)
        if (fieldsValid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(
                        symbol = symbol,
                        count = count,
                        price = price,
                        createDate = createDate
                    )
                    editInstrumentItemUseCase.editInstrumentItem(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun parsePrice(inputPrice: String?): Double {
        return try {
            inputPrice?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }
    private fun parseDate(inputDate: String?): Long {
        return try {
            inputDate?.trim()?.toLong() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int, price: Double): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        if (count <= 0.0) {
            _errorInputPrice.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    fun resetErrorInputPrice() {
        _errorInputPrice.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}