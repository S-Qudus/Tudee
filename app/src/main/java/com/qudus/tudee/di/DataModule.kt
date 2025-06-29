package com.qudus.tudee.di

import com.qudus.tudee.data.service.PreferencesManagerImpl
import com.qudus.tudee.domain.service.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    // Data layer dependencies
    single { PreferencesManagerImpl(androidContext()) } bind PreferencesManager::class
} 