package com.dstakhanov.app.di

import android.app.Application
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.info.presentation.InstrumentInfoDetailFragment
import com.dstakhanov.info.presentation.InstrumentInfoPriceListFragment
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemDetailFragment
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemFragment
import com.dstakhanov.findealscommithistory.presentation.portfolio.PortfolioFragment
import com.dstakhanov.findealscommithistory.presentation.reports.ReportFragment
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

    fun inject(fragment: com.dstakhanov.info.presentation.InstrumentInfoDetailFragment)

    fun inject(fragment: com.dstakhanov.info.presentation.InstrumentInfoPriceListFragment)

    fun inject(fragment: InstrumentItemFragment)

    fun inject(fragment: PortfolioFragment)

    fun inject(fragment: ReportFragment)

    fun inject(application: InstrumentApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }


}