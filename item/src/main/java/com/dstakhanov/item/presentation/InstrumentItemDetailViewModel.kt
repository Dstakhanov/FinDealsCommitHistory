package com.dstakhanov.item.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstakhanov.utils.DateUtility
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstrumentItemDetailViewModel @Inject constructor(
    private val getInstrumentItemUseCase: com.dstakhanov.item.domain.GetInstrumentItemUseCase,
    private val addInstrumentItemUseCase: com.dstakhanov.item.domain.AddInstrumentItemUseCase,
    private val editInstrumentItemUseCase: com.dstakhanov.item.domain.EditInstrumentItemUseCase
) : ViewModel() {
    @Inject
    lateinit var dateUtility: com.dstakhanov.utils.DateUtility

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _errorInputPrice = MutableLiveData<Boolean>()
    val errorInputPrice: LiveData<Boolean>
        get() = _errorInputPrice

    private val _instrumentItem = MutableLiveData<com.dstakhanov.item.domain.InstrumentItem>()
    val instrumentItem: LiveData<com.dstakhanov.item.domain.InstrumentItem>
        get() = _instrumentItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getInstrumentItem(instrumentItemId: Int) {
        viewModelScope.launch {
            val item = getInstrumentItemUseCase.getInstrumentItem(instrumentItemId)
            _instrumentItem.value = item
        }

    }

    fun addInstrumentItem(
        inputName: String?,
        inputCount: String?,
        inputPrice: String?,
        inputDate: String?,
        inputDirection: Int
    ) {
        val symbol = parseName(inputName)
        val count = parseCount(inputCount)
        val price = parsePrice(inputPrice)
        val createDate = parseDate(inputDate)
        val fieldsValid = validateInput(symbol, count, price)
        if (fieldsValid) {
            viewModelScope.launch {
                val instrumentItem =
                    com.dstakhanov.item.domain.InstrumentItem(
                        symbol,
                        count,
                        price,
                        createDate,
                        inputDirection,
                        true
                    )
                addInstrumentItemUseCase.addInstrumentItem(instrumentItem)
                finishWork()
            }
        }

    }

    fun editInstrumentItem(
        inputName: String?,
        inputCount: String?,
        inputPrice: String?,
        inputDate: String?,
        inputDirection: Int
    ) {
        val symbol = parseName(inputName)
        val count = parseCount(inputCount)
        val price = parsePrice(inputPrice)
        val createDate = parseDate(inputDate)
        val fieldsValid = validateInput(symbol, count, price)
        if (fieldsValid) {
            _instrumentItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(
                        symbol = symbol,
                        count = count,
                        price = price,
                        createDate = createDate,
                        direction = inputDirection
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

    private fun parseCount(inputCount: String?): Double {
        return try {
            inputCount?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun parsePrice(inputPrice: String?): Double {
        return try {
            inputPrice?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun parseDate(inputDate: String?): String {
        return if (inputDate != null && inputDate != "") inputDate
        else dateUtility.convertTimestampToDateTime(
            dateUtility.getCurrentDateInMills() / 1000L
        )
    }

    private fun validateInput(name: String, count: Double, price: Double): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0.0) {
            _errorInputCount.value = true
            result = false
        }
        if (price <= 0.0) {
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