package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskViewModel
import com.qudus.tudee.ui.screen.editCategoryScreen.EditCategoryViewModel
import com.qudus.tudee.ui.screen.addCategoryScreen.AddCategoryViewModel
import com.qudus.tudee.ui.screen.editTask.EditTaskViewModel
import com.qudus.tudee.ui.screen.onBoarding.OnBoardingViewModel
import com.qudus.tudee.ui.screen.task_details.TaskDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::EditTaskViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::AddCategoryViewModel)
    viewModelOf(::EditCategoryViewModel)

}