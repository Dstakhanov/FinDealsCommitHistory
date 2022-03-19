package com.dstakhanov.findealscommithistory.di

import androidx.lifecycle.ViewModel
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
    fun bindMainViewModel(viewModel: InstrumentItemMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InstrumentItemViewModel::class)
    fun bindShopItemViewModel(viewModel: InstrumentItemViewModel): ViewModel
}