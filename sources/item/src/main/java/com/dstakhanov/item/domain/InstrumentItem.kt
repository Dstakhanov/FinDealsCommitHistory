package com.dstakhanov.item.domain

data class InstrumentItem(
    val symbol: String,
    val count: Double,
    val price: Double,
    val createDate: String,
    val direction: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
