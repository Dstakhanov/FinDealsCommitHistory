package com.dstakhanov.portfolio.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.portfolio.presentation.PortfolioViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PortfolioViewModel::class)
    fun bindPortfolioViewModel(viewModel: PortfolioViewModel): ViewModel

}