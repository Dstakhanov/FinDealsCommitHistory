package com.dstakhanov.findealscommithistory.di

import android.app.Application
import com.dstakhanov.findealscommithistory.data.AppDatabase
import com.dstakhanov.findealscommithistory.data.info.InstrumentInfoDao
import com.dstakhanov.findealscommithistory.data.info.InstrumentInfoRepositoryImpl
import com.dstakhanov.findealscommithistory.data.info.network.ApiFactory
import com.dstakhanov.findealscommithistory.data.info.network.ApiService
import com.dstakhanov.findealscommithistory.data.item.InstrumentListDao
import com.dstakhanov.findealscommithistory.data.item.InstrumentListRepositoryImpl
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfoRepository
import com.dstakhanov.findealscommithistory.domain.item.InstrumentListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindInstrumentListRepository(impl: InstrumentListRepositoryImpl): InstrumentListRepository

    @Binds
    @ApplicationScope
    fun bindInstrumentRepository(impl: InstrumentInfoRepositoryImpl): InstrumentInfoRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideInstrumentInfoDao(
            application: Application
        ): InstrumentInfoDao {
            return AppDatabase.getInstance(application).instrumentPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideInstrumentListDao(
            application: Application
        ): InstrumentListDao {
            return AppDatabase.getInstance(application).instrumentListDao()
        }
    }
}