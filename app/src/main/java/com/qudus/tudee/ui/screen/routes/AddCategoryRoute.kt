package com.qudus.tudee.ui.screen.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

private val addCategoryRoute = Screen.AddCategoryScreen.route

fun NavController.navigateToAddCategoryScreen(){
    navigate(addCategoryRoute)
}

fun NavGraphBuilder.addCategoryRoute(navController: NavController) {
    composable(
        route = addCategoryRoute,
    ) {
        // AddCategoryScreen()
    }
}


