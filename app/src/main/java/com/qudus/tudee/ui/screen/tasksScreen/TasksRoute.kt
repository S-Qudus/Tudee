package com.qudus.tudee.ui.screen.tasksScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.tasksRoute(navController: NavController){
    composable(
        route = Screen.TasksScreen.route
    ) {
        TasksScreen(navController)
    }
}