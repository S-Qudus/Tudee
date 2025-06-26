package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.task_details.TaskDetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
    singleOf(::TaskDetailsViewModel)
}