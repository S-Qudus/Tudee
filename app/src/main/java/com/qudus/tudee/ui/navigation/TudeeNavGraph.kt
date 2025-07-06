package com.qudus.tudee.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.qudus.tudee.ui.designSystem.component.BottomNavBar
import com.qudus.tudee.ui.screen.HomeScreen.component.getBottomNavItems
import com.qudus.tudee.ui.screen.onBoarding.onBoardingRoute
import com.qudus.tudee.ui.screen.addCategoryScreen.addCategoryRoute
import com.qudus.tudee.ui.screen.routes.categoriesRoute
import com.qudus.tudee.ui.screen.editCategoryScreen.editCategoryRoute
import com.qudus.tudee.ui.screen.HomeScreen.homeRoute
import com.qudus.tudee.ui.screen.configration.ConfigurationViewModel
import com.qudus.tudee.ui.screen.tasksScreen.tasksRoute
import org.koin.androidx.compose.koinViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeNavGraph(
    navHostController: NavHostController,
    viewModel: ConfigurationViewModel = koinViewModel()
) {
    val startDestination = viewModel.uiState.collectAsState().value.startDestination
    if (startDestination == null) return // splash is still shown

    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomNavItems = getBottomNavItems()
    val isBottomBarVisible = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavBar(
                    navController = navHostController,
                    items = bottomNavItems,
                    selectedRoute = currentRoute ?: ""
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
        ) {
            homeRoute(navHostController)
            tasksRoute(navHostController)
            categoriesRoute(navHostController)
            onBoardingRoute(navHostController)
            addCategoryRoute(navHostController)
            editCategoryRoute(navHostController)
        }
    }
}