package com.qudus.tudee.ui.navigation

sealed class Screen(val route: String) {
    object OnBoardingScreen: Screen("onBoardingScreen")
    object HomeScreen: Screen("homeScreen")
    object AddTaskScreen: Screen("addTaskScreen")
    object EditTaskScreen: Screen("editTaskScreen")
}