package com.qudus.tudee.ui.navigation

sealed class Screen(val route: String) {
    object OnBoardingScreen: Screen("onBoardingScreen")
    object HomeScreen: Screen("homeScreen")
    object TasksScreen: Screen("tasksScreen")
    object CategoriesScreen: Screen("categoriesScreen")
    object AddTaskScreen: Screen("addTaskScreen")
    object EditTaskScreen: Screen("editTaskScreen")
    object TaskDetailsScreen: Screen("taskDetailsScreen")
    object AddCategoryScreen: Screen("addCategoryScreen")
    object EditCategoryScreen: Screen("editCategoryScreen")
}