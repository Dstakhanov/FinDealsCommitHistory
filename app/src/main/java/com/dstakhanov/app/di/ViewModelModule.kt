package com.dstakhanov.app.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.info.presentation.InstrumentInfoViewModel
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemViewModel
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemDetailViewModel
import com.dstakhanov.findealscommithistory.presentation.portfolio.PortfolioViewModel
import com.dstakhanov.findealscommithistory.presentation.reports.ReportViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(com.dstakhanov.info.presentation.InstrumentInfoViewModel::class)
    fun bindInstrumentInfoViewModel(viewModel: com.dstakhanov.info.presentation.InstrumentInfoViewModel): ViewModel
   
    @Binds
    @IntoMap
    @ViewModelKey(PortfolioViewModel::class)
    fun bindPortfolioViewModel(viewModel: PortfolioViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReportViewModel::class)
    fun bindReportViewModel(viewModel:ReportViewModel): ViewModel
}