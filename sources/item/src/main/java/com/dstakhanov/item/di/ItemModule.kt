package com.dstakhanov.item.di

import android.app.Application
import com.dstakhanov.core.AppDatabase
import com.dstakhanov.core.item.InstrumentListDao
import com.dstakhanov.item.data.InstrumentListRepositoryImpl
import com.dstakhanov.item.domain.InstrumentListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
interface ItemModule {

    @Singleton
    @Binds
    fun bindInstrumentListRepository(impl: InstrumentListRepositoryImpl): InstrumentListRepository

    companion object {

        @Singleton
        @Provides
        fun provideInstrumentListDao(
            application: Application
        ): InstrumentListDao {
            return AppDatabase.getInstance(application).instrumentListDao()
        }
    }
}