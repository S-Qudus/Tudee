package com.qudus.tudee.app.di

import com.qudus.tudee.ui.screen.categorysheet.AddCategoryViewModel
import com.qudus.tudee.ui.screen.categorysheet.EditCategoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module{
    viewModel { AddCategoryViewModel(get()) }
    viewModel { EditCategoryViewModel(get()) }

}