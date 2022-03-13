package com.dstakhanov.findealscommithistory.data.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instrument_items")
data class InstrumentItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val symbol: String,
    val count: Int,
    val price: Double,
    val createDate: Long,
    val enabled: Boolean,
)