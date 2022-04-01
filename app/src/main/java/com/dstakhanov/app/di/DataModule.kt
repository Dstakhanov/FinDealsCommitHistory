package com.dstakhanov.app.di

import android.app.Application
import com.dstakhanov.core.AppDatabase
import com.dstakhanov.info.data.InstrumentInfoDao
import com.dstakhanov.info.data.InstrumentInfoRepositoryImpl
import com.dstakhanov.api.ApiFactory
import com.dstakhanov.api.ApiService
import com.dstakhanov.findealscommithistory.data.item.InstrumentListDao
import com.dstakhanov.findealscommithistory.data.item.InstrumentListRepositoryImpl
import com.dstakhanov.info.domain.InstrumentInfoRepository
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
    fun bindInstrumentRepository(impl: com.dstakhanov.info.data.InstrumentInfoRepositoryImpl): com.dstakhanov.info.domain.InstrumentInfoRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideInstrumentInfoDao(
            application: Application
        ): com.dstakhanov.info.data.InstrumentInfoDao {
            return com.dstakhanov.core.AppDatabase.getInstance(application).instrumentPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): com.dstakhanov.api.ApiService {
            return com.dstakhanov.api.ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideInstrumentListDao(
            application: Application
        ): InstrumentListDao {
            return com.dstakhanov.core.AppDatabase.getInstance(application).instrumentListDao()
        }
    }
}