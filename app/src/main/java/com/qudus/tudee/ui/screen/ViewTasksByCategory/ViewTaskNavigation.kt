package com.qudus.tudee.ui.screen.ViewTasksByCategory



import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val VIEW_TASK_ROUTE = "view_task"

fun NavGraphBuilder.viewTaskGraph(
    navController: NavController,
    onBackClick: () -> Unit,
    onEditCategory: (Long) -> Unit,
    onTaskClick: (Long) -> Unit
) {
    composable(
        route = "$VIEW_TASK_ROUTE/{categoryId}",
        arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
    ) { backStackEntry ->
        val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: 0L
        ViewTaskScreen(
            categoryId = categoryId,
            onBackClick = onBackClick,
            onEditCategory = onEditCategory,

        )
    }
}