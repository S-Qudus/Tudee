package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.task_details.TaskDetailsViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::TaskDetailsViewModel)
    viewModelOf(::AddTaskViewModel)
}