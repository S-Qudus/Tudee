package com.qudus.tudee.ui.screen.routes

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.qudus.tudee.ui.navigation.Screen

private val editTaskRoute = Screen.EditTaskScreen.route

fun NavController.navigateToEditTaskScreen(taskId: Long) {
    navigate(route = "$editTaskRoute/$taskId")
}

fun NavGraphBuilder.editTaskRoute(navController: NavController) {
    composable(
        route = "$editTaskRoute/${EditTaskArgs.TASK_ID_ARG}",
        arguments = listOf(
            navArgument(name = EditTaskArgs.TASK_ID_ARG) { NavType.LongType }
        ),
    ) {
        // EditTaskScreen()
    }
}

class EditTaskArgs(savedStateHandle: SavedStateHandle) {
    val taskId: Long = checkNotNull(savedStateHandle[TASK_ID_ARG])

    companion object {
        const val TASK_ID_ARG = "taskId"
    }
}