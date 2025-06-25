package com.qudus.tudee.app

import android.app.Application
import com.qudus.tudee.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TudeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TudeeApplication)
            modules(appModule)
        }
    }
}