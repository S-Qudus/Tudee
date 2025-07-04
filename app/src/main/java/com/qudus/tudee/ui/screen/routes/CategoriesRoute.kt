package com.qudus.tudee.ui.screen.routes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen
import com.qudus.tudee.ui.screen.categories.CategoriesScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.categoriesRoute(navController: NavController){
    composable(
        route = Screen.CategoriesScreen.route
    ) {
        CategoriesScreen(navController = navController)
    }
}