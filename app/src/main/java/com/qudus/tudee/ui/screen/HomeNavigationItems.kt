package com.qudus.tudee.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.model.BottomNavItem

@Composable
fun getBottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            route = "home",
            iconFill = painterResource(id = R.drawable.icon_home_filled),
            iconStroke = painterResource(id = R.drawable.icon_home_stroke)
        ),
        BottomNavItem(
            route = "tasks",
            iconFill = painterResource(id = R.drawable.icon_note_filled),
            iconStroke = painterResource(id = R.drawable.icon_note_stroke)
        ),
        BottomNavItem(
            route = "profile",
            iconFill = painterResource(id = R.drawable.icon_more_filled),
            iconStroke = painterResource(id = R.drawable.icon_more_stroke)
        )
    )
} 