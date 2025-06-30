package com.qudus.tudee.di

import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    // UI layer dependencies
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AddTaskViewModel(get(), get(), get(), get()) }
} 