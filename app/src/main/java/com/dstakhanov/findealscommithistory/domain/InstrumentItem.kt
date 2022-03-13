package com.dstakhanov.findealscommithistory.domain

data class InstrumentItem(
    val symbol: String,
    val count: Int,
    val price: Double,
    val date: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
