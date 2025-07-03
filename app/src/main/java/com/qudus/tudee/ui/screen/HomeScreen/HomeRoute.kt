package com.qudus.tudee.ui.screen.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

fun NavController.navigateToHomeScreen(){
    navigate(route = Screen.HomeScreen.route){
        popUpTo(Screen.OnBoardingScreen.route) {
            inclusive = true
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeRoute(navController: NavController){
    composable(
        route = Screen.HomeScreen.route
    ) {
        HomeScreen(navController = navController)
    }
}