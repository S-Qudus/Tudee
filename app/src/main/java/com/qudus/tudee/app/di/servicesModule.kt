package com.qudus.tudee.app.di

import com.qudus.tudee.data.service.PreferencesManagerImpl
import com.qudus.tudee.domain.service.PreferencesManager
import org.koin.dsl.module

val servicesModule = module {
    single<PreferencesManager> { PreferencesManagerImpl(get()) }
}