package com.dstakhanov.item.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.item.presentation.InstrumentItemDetailViewModel
import com.dstakhanov.item.presentation.InstrumentItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InstrumentItemViewModel::class)
    fun bindInstrumentItemMainViewModel(viewModel: InstrumentItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InstrumentItemDetailViewModel::class)
    fun bindInstrumentItemViewModel(detailViewModel: InstrumentItemDetailViewModel): ViewModel

}