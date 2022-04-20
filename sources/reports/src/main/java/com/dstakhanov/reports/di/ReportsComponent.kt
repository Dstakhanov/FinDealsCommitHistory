package com.dstakhanov.reports.di

import com.dstakhanov.reports.presentation.ReportFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
    ]
)
interface ReportsComponent {

    fun inject(fragment: ReportFragment)

    @Component.Factory
    interface Factory {

        fun create(): ReportsComponent
    }


}