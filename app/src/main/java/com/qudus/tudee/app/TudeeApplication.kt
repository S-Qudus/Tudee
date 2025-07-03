package com.qudus.tudee.app

import android.app.Application
import com.qudus.tudee.app.di.dataModule
import com.qudus.tudee.app.di.servicesModule
import com.qudus.tudee.app.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TudeeApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TudeeApplication)
            modules(
                dataModule,
                servicesModule,
                uiModule
            )
        }
    }
}
