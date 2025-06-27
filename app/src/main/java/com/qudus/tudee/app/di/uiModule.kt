package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.tasksScreen.TaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { TaskViewModel(get()) }
}