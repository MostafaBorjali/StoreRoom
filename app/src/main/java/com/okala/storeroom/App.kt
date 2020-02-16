package com.okala.storeroom

import android.app.Application
import com.facebook.stetho.Stetho
import com.okala.storeroom.di.appComponent
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
        configureDi()
    }

    private fun configureDi() {
        startKoin(this, provideComponent())
    }

    open fun provideComponent() = appComponent
}