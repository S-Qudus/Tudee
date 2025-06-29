package com.qudus.tudee.di

import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    // UI layer dependencies
    viewModel { HomeViewModel(get()) }
} 