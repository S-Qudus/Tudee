package com.qudus.tudee.ui.screen.routes

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.qudus.tudee.ui.navigation.Screen

private val editCategoryRoute = Screen.EditCategoryScreen.route

fun NavController.navigateToEditCategoryScreen(taskId: Long) {
    navigate(route = "$editCategoryRoute/$taskId")
}

fun NavGraphBuilder.editCategoryRoute(navController: NavController) {
    composable(
        route = "$editCategoryRoute/${EditCategoryArgs.CATEGORY_ID_ARG}",
        arguments = listOf(
            navArgument(name = EditCategoryArgs.CATEGORY_ID_ARG) { NavType.LongType }
        ),
    ) {
        // EditCategoryScreen()
    }
}

class EditCategoryArgs(savedStateHandle: SavedStateHandle) {
    val categoryId: Long = checkNotNull(savedStateHandle[CATEGORY_ID_ARG])

    companion object {
        const val CATEGORY_ID_ARG = "categoryId"
    }
}