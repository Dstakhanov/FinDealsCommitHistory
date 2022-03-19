package com.dstakhanov.findealscommithistory.presentation

import android.app.Application

class InstrumentApp: Application(){

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}