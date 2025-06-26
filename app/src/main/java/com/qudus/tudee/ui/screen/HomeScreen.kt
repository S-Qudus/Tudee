package com.qudus.tudee.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.TudeeHeader.TudeeHeader
import com.qudus.tudee.ui.designSystem.component.ThemeSwitchButton.ThemeSwitchButton
import com.qudus.tudee.ui.designSystem.component.TudeeScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.ui.composable.TudeeIconButton
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import androidx.compose.ui.Alignment
import com.qudus.tudee.ui.designSystem.component.BottomNavBar
import androidx.navigation.NavController
import com.qudus.tudee.ui.designSystem.model.BottomNavItem

@Composable
fun HomeScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }

    TudeeScaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(167.dp)
                    .background(Theme.color.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TudeeHeader(
                    modifier = Modifier
                        .fillMaxWidth(),
                    endContent = {
                        ThemeSwitchButton(
                            isDarkMode = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            modifier = Modifier
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            TudeeIconButton(
                onClickIconButton = { },
                isEnabled = true,
                isLoading = false,
                icon = painterResource(id = R.drawable.icon_note_add),
                contentDescription = stringResource(R.string.add_task),
                hasShadow = true
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = getBottomNavItems(),
                selectedRoute = "home"
            )
        },
        content = {


        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(
        context = TODO()
    ))
}