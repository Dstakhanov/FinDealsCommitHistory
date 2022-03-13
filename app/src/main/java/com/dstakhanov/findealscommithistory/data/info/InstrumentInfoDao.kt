package com.dstakhanov.findealscommithistory.data.info

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InstrumentInfoDao {
    @Query("SELECT * FROM price_list ORDER BY lastUpdate Desc")
    fun getPriceList(): LiveData<List<InstrumentInfoDbModel>>

    @Query("SELECT * FROM price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutInstrument(fSym: String): LiveData<InstrumentInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<InstrumentInfoDbModel>)
}