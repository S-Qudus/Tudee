package com.qudus.tudee.ui.screen.addTask

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen


fun NavController.navigateToAddTaskScreen(){
    navigate(route = Screen.AddTaskScreen.route)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.addTaskRoute(navController: NavController){
    composable(route = Screen.AddTaskScreen.route){
        AddTaskScreen()
    }
}