package com.dstakhanov.info.di

import android.app.Application
import com.dstakhanov.info.presentation.InstrumentInfoDetailFragment
import com.dstakhanov.info.presentation.InstrumentInfoPriceListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InfoModule::class,
        ViewModelModule::class,
    ]
)
interface InfoComponent {

    fun inject(fragment: InstrumentInfoDetailFragment)

    fun inject(fragment: InstrumentInfoPriceListFragment)


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): InfoComponent
    }


}