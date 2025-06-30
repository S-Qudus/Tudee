package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import com.qudus.tudee.ui.screen.onBoarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel(
        get(),
        get()
    ) }
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::OnBoardingViewModel)
}