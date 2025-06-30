package com.qudus.tudee.app.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.ui.screen.tasksScreen.viewModel.TaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val uiModule = module {
    viewModel { TaskViewModel(get()) }
}