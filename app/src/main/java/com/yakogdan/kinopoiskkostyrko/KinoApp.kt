package com.yakogdan.kinopoiskkostyrko

import android.app.Application
import com.yakogdan.kinopoiskkostyrko.di.ApplicationComponent
import com.yakogdan.kinopoiskkostyrko.di.DaggerApplicationComponent

class KinoApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}