package com.dstakhanov.findealscommithistory.di

import android.app.Application
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.MainActivity
import com.dstakhanov.findealscommithistory.presentation.info.InstrumentInfoDetailFragment
import com.dstakhanov.findealscommithistory.presentation.info.InstrumentInfoPriceListActivity
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemFragment
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemMainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: InstrumentItemMainActivity)

    fun inject(fragment: InstrumentItemFragment)

    fun inject(activity: InstrumentInfoPriceListActivity)

    fun inject(fragment: InstrumentInfoDetailFragment)

    fun inject(application: InstrumentApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }


}