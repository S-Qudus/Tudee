package com.qudus.tudee.app.di

import com.qudus.tudee.data.service.CategoryServiceImpl
import com.qudus.tudee.data.service.InputValidator
import com.qudus.tudee.data.service.TaskServiceImpl
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import com.qudus.tudee.data.service.PreferencesManagerImpl
import com.qudus.tudee.domain.service.PreferencesManager
import org.koin.dsl.module

val servicesModule = module{
    singleOf(::CategoryServiceImpl) { bind<CategoryService>() }
    singleOf(::TaskServiceImpl) { bind<TaskService>() }
    single { InputValidator() }
    single<PreferencesManager> { PreferencesManagerImpl(get()) }
}