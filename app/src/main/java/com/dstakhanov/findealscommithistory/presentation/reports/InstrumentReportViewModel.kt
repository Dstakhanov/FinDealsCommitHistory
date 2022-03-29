package com.dstakhanov.findealscommithistory.presentation.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class InstrumentReportViewModel @Inject constructor(

) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reports fragment"
    }
    val text: LiveData<String> = _text

}