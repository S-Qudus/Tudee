package com.qudus.tudee.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qudus.tudee.ui.screen.addTask.addTaskRoute
import com.qudus.tudee.ui.screen.onBoarding.onBoardingRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = "onBoardingScreen"){
        addTaskRoute()
        onBoardingRoute(navHostController)
    }
}