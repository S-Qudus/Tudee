package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import com.qudus.tudee.ui.screen.onBoarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModelOf
import com.qudus.tudee.ui.screen.categorysheet.AddCategoryViewModel
import com.qudus.tudee.ui.screen.categorysheet.EditCategoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module{
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModel { AddCategoryViewModel(get()) }
    viewModel { EditCategoryViewModel(get()) }

}