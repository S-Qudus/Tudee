package com.qudus.tudee.app

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.app.di.appModule
import com.qudus.tudee.app.di.dataModule
import com.qudus.tudee.app.di.servicesModule
import com.qudus.tudee.app.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@RequiresApi(Build.VERSION_CODES.O)
class TudeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TudeeApplication)
            modules(appModule)
        }
    }
}
