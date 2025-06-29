package com.qudus.tudee.app

import android.app.Application
import com.qudus.tudee.di.appModule
import com.qudus.tudee.di.dataModule
import com.qudus.tudee.di.domainModule
import com.qudus.tudee.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TudeeApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TudeeApplication)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule,
                    uiModule
                )
            )
        }
    }
}
