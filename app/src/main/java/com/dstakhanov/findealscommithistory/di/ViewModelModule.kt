package com.dstakhanov.findealscommithistory.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.findealscommithistory.presentation.info.InstrumentInfoViewModel
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemMainViewModel
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InstrumentItemMainViewModel::class)
    fun bindInstrumentItemMainViewModel(viewModel: InstrumentItemMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InstrumentItemViewModel::class)
    fun bindInstrumentItemViewModel(viewModel: InstrumentItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InstrumentInfoViewModel::class)
    fun bindInstrumentInfoViewModel(viewModel:InstrumentInfoViewModel): ViewModel
}