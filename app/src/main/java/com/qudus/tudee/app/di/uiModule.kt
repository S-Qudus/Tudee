package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import com.qudus.tudee.ui.screen.editTask.EditTaskViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module{
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::EditTaskViewModel)
}