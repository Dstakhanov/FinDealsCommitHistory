package com.dstakhanov.findealscommithistory.di

import android.app.Application
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.info.InstrumentInfoDetailFragment
import com.dstakhanov.findealscommithistory.presentation.info.InstrumentInfoPriceListFragment
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemDetailFragment
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemFragment
import com.dstakhanov.findealscommithistory.presentation.reports.InstrumentReportFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(detailFragment: InstrumentItemDetailFragment)

    fun inject(fragment: InstrumentInfoDetailFragment)

    fun inject(fragment: InstrumentInfoPriceListFragment)

    fun inject(fragment: InstrumentItemFragment)

    fun inject(fragment: InstrumentReportFragment)

    fun inject(application: InstrumentApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }


}