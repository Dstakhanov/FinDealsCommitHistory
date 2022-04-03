package com.dstakhanov.item.di

import android.app.Application
import com.dstakhanov.item.presentation.InstrumentItemDetailFragment
import com.dstakhanov.item.presentation.InstrumentItemFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ItemModule::class,
        ViewModelModule::class,
    ]
)
interface ItemComponent {

    fun inject(detailFragment: InstrumentItemDetailFragment)

    fun inject(fragment: InstrumentItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ItemComponent
    }


}