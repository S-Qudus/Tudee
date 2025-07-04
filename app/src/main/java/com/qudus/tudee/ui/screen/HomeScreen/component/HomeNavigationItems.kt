package com.qudus.tudee.ui.screen.HomeScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.model.BottomNavItem
import com.qudus.tudee.ui.navigation.Screen

@Composable
fun getBottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            iconFill = painterResource(id = R.drawable.icon_home_filled),
            iconStroke = painterResource(id = R.drawable.icon_home_stroke)
        ),
        BottomNavItem(
            route = Screen.TasksScreen.route,
            iconFill = painterResource(id = R.drawable.icon_note_filled),
            iconStroke = painterResource(id = R.drawable.icon_note_stroke)
        ),
        BottomNavItem(
            route = Screen.CategoriesScreen.route,
            iconFill = painterResource(id = R.drawable.icon_more_filled),
            iconStroke = painterResource(id = R.drawable.icon_more_stroke)
        )
    )
} 