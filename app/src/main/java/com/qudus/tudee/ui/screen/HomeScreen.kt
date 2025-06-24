package com.qudus.tudee.ui.screen

import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.model.BottomNavItem
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.qudus.tudee.designSystem.component.BottomNavBar

@Composable
fun HomeScreen() {

    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem(
            route = "home",
            iconFill = painterResource(R.drawable.icon_home_filled),
            iconStroke = painterResource(R.drawable.icon_home_stroke)
        ),
        BottomNavItem(
            route = "note",
            iconFill = painterResource(R.drawable.icon_note_filled),
            iconStroke = painterResource(R.drawable.icon_note_stroke)
        ),
        BottomNavItem(
            route = "more",
            iconFill = painterResource(R.drawable.icon_more_filled),
            iconStroke = painterResource(R.drawable.icon_more_stroke)
        )
    )
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value
        ?.destination?.route ?: "home"

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Text("Home Screen") }
        composable("note") { Text("Note Screen") }
        composable("more") { Text("More Screen") }
    }

    BottomNavBar(
    navController = navController,
    items = items,
    selectedRoute = currentRoute
    )

}