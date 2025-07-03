package com.qudus.tudee.ui.screen.onBoarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen

private val onBoardingRoute = Screen.OnBoardingScreen.route

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(route = onBoardingRoute) {
        OnBoardingScreen(navController = navController)
    }
}

