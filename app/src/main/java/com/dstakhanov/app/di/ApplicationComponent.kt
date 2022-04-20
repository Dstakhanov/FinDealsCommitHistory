package com.dstakhanov.app.di

import android.app.Application
import com.dstakhanov.app.InstrumentApp
import com.dstakhanov.info.di.WorkerModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: InstrumentApp)

    @Component.Factory
    interface Factory {

        fun create(): ApplicationComponent
    }


}