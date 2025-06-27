package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.onBoarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { OnBoardingViewModel(get()) }
}