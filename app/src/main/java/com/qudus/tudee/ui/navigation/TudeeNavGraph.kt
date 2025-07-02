package com.qudus.tudee.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qudus.tudee.ui.screen.addTask.addTaskRoute
import com.qudus.tudee.ui.screen.onBoarding.onBoardingRoute
import com.qudus.tudee.ui.screen.routes.addCategoryRoute
import com.qudus.tudee.ui.screen.routes.categoriesRoute
import com.qudus.tudee.ui.screen.routes.editCategoryRoute
import com.qudus.tudee.ui.screen.routes.editTaskRoute
import com.qudus.tudee.ui.screen.routes.homeRoute
import com.qudus.tudee.ui.screen.routes.taskDetailsRoute
import com.qudus.tudee.ui.screen.routes.tasksRoute
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeNavGraph(navHostController: NavHostController
, viewModel: NavViewModel = koinViewModel()
){
    val startDestination by viewModel.state.collectAsState()
    NavHost(navController = navHostController, startDestination = startDestination){
        onBoardingRoute(navHostController)
        homeRoute(navHostController)
        tasksRoute(navHostController)
        categoriesRoute(navHostController)
        addTaskRoute(navHostController)
        editTaskRoute(navHostController)
        taskDetailsRoute(navHostController)
        addCategoryRoute(navHostController)
        editCategoryRoute(navHostController)
    }
}