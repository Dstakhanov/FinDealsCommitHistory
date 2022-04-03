package com.dstakhanov.info.di

import android.app.Application
import com.dstakhanov.api.ApiFactory
import com.dstakhanov.api.ApiService
import com.dstakhanov.core.AppDatabase
import com.dstakhanov.core.info.InstrumentInfoDao
import com.dstakhanov.core.info.InstrumentInfoRepositoryImpl
import com.dstakhanov.info.domain.InstrumentInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface InfoModule {



    @Binds
    @Singleton
    fun bindInstrumentRepository(impl: InstrumentInfoRepositoryImpl): InstrumentInfoRepository

    companion object {
        @Provides
        @Singleton
        fun provideInstrumentInfoDao(
            application: Application
        ): InstrumentInfoDao {
            return AppDatabase.getInstance(application).instrumentPriceInfoDao()
        }

        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }
}