package com.qudus.tudee.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qudus.tudee.ui.screen.addTask.addTaskRoute
import com.qudus.tudee.ui.screen.onBoarding.onBoardingRoute
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeNavGraph(navHostController: NavHostController
, viewModel: NavViewModel = koinViewModel()
){
    val startDestination by viewModel.state.collectAsState()
    NavHost(navController = navHostController, startDestination = startDestination){
        addTaskRoute(navHostController)
        onBoardingRoute(navHostController)
    }
}