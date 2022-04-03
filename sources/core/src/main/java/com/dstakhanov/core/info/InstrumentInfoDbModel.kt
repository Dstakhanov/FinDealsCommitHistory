package com.dstakhanov.core.info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list")
data class InstrumentInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: Long?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
)
