package com.qudus.tudee.app.di

import com.qudus.tudee.data.service.TaskServiceImpl
import com.qudus.tudee.domain.service.TaskService
import org.koin.dsl.module

val servicesModule = module{
    single<TaskService> { TaskServiceImpl(get()) }
}