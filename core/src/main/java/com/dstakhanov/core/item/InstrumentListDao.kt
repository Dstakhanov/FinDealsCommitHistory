package com.dstakhanov.core.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InstrumentListDao {
    @Query("SELECT * FROM instrument_items")
    fun getInstrumentList(): LiveData<List<InstrumentItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInstrumentItem(instrumentItemDbModel: InstrumentItemDbModel)

    @Query("DELETE FROM instrument_items WHERE id=:instrumentItemId")
    suspend fun deleteInstrumentItem(instrumentItemId: Int)

    @Query("SELECT * FROM instrument_items WHERE id=:instrumentItemId LIMIT 1")
    suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItemDbModel
}