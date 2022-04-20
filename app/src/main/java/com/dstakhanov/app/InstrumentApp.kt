package com.dstakhanov.app

import android.app.Application
import androidx.work.Configuration
import com.dstakhanov.app.di.ApplicationComponent
import com.dstakhanov.info.workers.InstrumentWorkerFactory
import javax.inject.Inject

class InstrumentApp : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: InstrumentWorkerFactory

    private lateinit var component: ApplicationComponent

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