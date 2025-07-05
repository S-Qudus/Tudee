package com.qudus.tudee.ui.screen.ViewTasksByCategory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

fun NavController.navigateToViewTaskScreen(){
    navigate(route = Screen.ViewTasksScreen.route){
        popUpTo(Screen.CategoriesScreen.route) {
            inclusive = true
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.viewTasksRoute(navController: NavController){
    composable(
        route = Screen.HomeScreen.route
    ) {
        ViewTaskScreen(
            navController = navController,
            categoryId = 1,
            onBackClick = {  },
            onEditCategory = {  },
            viewModel = viewModel()
        )
    }
}