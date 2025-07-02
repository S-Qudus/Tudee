package com.qudus.tudee.ui.screen.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

fun NavController.popUpToTasksScreen(value: Boolean){
    previousBackStackEntry?.savedStateHandle
        ?.set("add_result",value)
    popBackStack()
}

fun NavGraphBuilder.tasksRoute(navController: NavController){
    composable(
        route = Screen.TasksScreen.route
    ) {
        // TasksScreen()
    }
}