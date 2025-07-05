package com.qudus.tudee.ui.screen.task_details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.qudus.tudee.ui.navigation.Screen

private val taskDetailsRoute = Screen.TaskDetailsScreen.route

fun NavController.navigateToTaskDetailsScreen(taskId: Long) {
    navigate(route = "$taskDetailsRoute/$taskId")
}

fun NavGraphBuilder.taskDetailsRoute(navController: NavController) {
    composable(
        route = "$taskDetailsRoute/{${TaskDetailsArgs.TASK_ID_ARG}}",
        arguments = listOf(
            navArgument(name = TaskDetailsArgs.TASK_ID_ARG) { NavType.LongType }
        ),
    ) {
//        TaskDetailsScreen(navController = navController, taskId = 0, onDismiss = {}, onEditTaskClick = {})
    }
}

class TaskDetailsArgs(savedStateHandle: SavedStateHandle) {
    val taskId: Long = checkNotNull(savedStateHandle[TASK_ID_ARG])

    companion object {
        const val TASK_ID_ARG = "taskId"
    }
}