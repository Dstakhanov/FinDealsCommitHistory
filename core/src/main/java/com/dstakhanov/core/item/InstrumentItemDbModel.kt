package com.dstakhanov.core.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instrument_items")
data class InstrumentItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val symbol: String,
    val count: Double,
    val price: Double,
    val createDate: Long,
    val enabled: Boolean,
    val direction: Int
)