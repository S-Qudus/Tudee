package com.qudus.tudee.app

import android.app.Application
import com.qudus.tudee.app.di.appModule
import com.qudus.tudee.app.di.dataModule
import com.qudus.tudee.app.di.servicesModule
import com.qudus.tudee.app.di.uiModule
import com.qudus.tudee.di.domainModule
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
                dataModule,
                domainModule,
                servicesModule,
                uiModule
            )
        }
    }
}
