package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.onBoarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::OnBoardingViewModel)
}