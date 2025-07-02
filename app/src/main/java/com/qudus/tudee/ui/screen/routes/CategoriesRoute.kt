package com.qudus.tudee.ui.screen.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

fun NavGraphBuilder.categoriesRoute(navController: NavController){
    composable(
        route = Screen.CategoriesScreen.route
    ) {
        // HomeScreen()
    }
}