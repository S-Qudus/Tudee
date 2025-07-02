package com.qudus.tudee.app.di

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
val appModule = listOf(
    dataModule,
    servicesModule,
    uiModule
)

