package com.qudus.tudee.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeIconButton
import com.qudus.tudee.ui.designSystem.component.BottomNavBar
import com.qudus.tudee.ui.designSystem.component.ThemeSwitchButton.ThemeSwitchButton
import com.qudus.tudee.ui.designSystem.component.TudeeHeader.TudeeHeader
import com.qudus.tudee.ui.designSystem.component.TudeeScaffold
import com.qudus.tudee.ui.designSystem.theme.Dimension
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.components.OverviewSection
import com.qudus.tudee.ui.screen.components.TaskSection
import com.qudus.tudee.ui.screen.data.getInProgressTasks
import com.qudus.tudee.ui.screen.data.getToDoTasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
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
                    modifier = Modifier.fillMaxWidth(),
                    endContent = {
                        ThemeSwitchButton(
                            isDarkMode = isDarkTheme,
                            onCheckedChange = onThemeChange,
                            modifier = Modifier
                        )
                    }
                )
            }
        },
        contentBackground = Theme.color.surface,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = Dimension.large)
            ) {
                OverviewSection(
                    completedTasks = 3,
                    totalTasks = 10
                )
                
                Spacer(modifier = Modifier.height(Dimension.large))
                
                TaskSection(
                    title = stringResource(R.string.in_progress),
                    taskCount = 3,
                    tasks = getInProgressTasks(),
                    onTaskClick = { taskId -> }
                )
                
                Spacer(modifier = Modifier.height(Dimension.large))
                
                TaskSection(
                    title = stringResource(R.string.to_do),
                    taskCount = 5,
                    tasks = getToDoTasks(),
                    onTaskClick = { taskId -> }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        isDarkTheme = false,
        onThemeChange = { }
    )
}


