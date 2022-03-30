package com.dstakhanov.findealscommithistory.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dstakhanov.findealscommithistory.data.info.InstrumentInfoDao
import com.dstakhanov.findealscommithistory.data.info.InstrumentInfoDbModel
import com.dstakhanov.findealscommithistory.data.item.InstrumentItemDbModel
import com.dstakhanov.findealscommithistory.data.item.InstrumentListDao

@Database(entities = [InstrumentItemDbModel::class, InstrumentInfoDbModel::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun instrumentListDao(): InstrumentListDao
    abstract fun instrumentPriceInfoDao(): InstrumentInfoDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "main.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}