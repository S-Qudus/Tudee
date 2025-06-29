package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel(get()) }
}