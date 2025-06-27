package com.qudus.tudee.app.di

import com.qudus.tudee.data.service.PreferenceServiceImpl
import com.qudus.tudee.domain.service.PreferenceService
import org.koin.dsl.module

val servicesModule = module{
    single<PreferenceService> { PreferenceServiceImpl(get()) }
}