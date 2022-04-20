package com.dstakhanov.info.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.info.presentation.InstrumentInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(InstrumentInfoViewModel::class)
    fun bindInstrumentInfoViewModel(viewModel: InstrumentInfoViewModel): ViewModel

}