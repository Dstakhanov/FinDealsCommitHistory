package com.dstakhanov.reports.di

import androidx.lifecycle.ViewModel
import com.dstakhanov.reports.presentation.ReportViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReportViewModel::class)
    fun bindReportViewModel(viewModel:ReportViewModel): ViewModel
}