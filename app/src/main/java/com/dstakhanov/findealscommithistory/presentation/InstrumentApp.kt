package com.dstakhanov.findealscommithistory.presentation

import android.app.Application
import androidx.work.Configuration
import com.dstakhanov.findealscommithistory.di.DaggerApplicationComponent
import com.dstakhanov.findealscommithistory.workers.InstrumentWorkerFactory
import javax.inject.Inject

class InstrumentApp : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: InstrumentWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

}