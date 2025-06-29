package com.qudus.tudee.ui.screen.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.qudus.tudee.ui.screen.components.EmptyStateScreen
import com.qudus.tudee.ui.screen.components.HomeOverviewCard
import com.qudus.tudee.ui.screen.components.TaskSection
import com.qudus.tudee.ui.screen.getBottomNavItems
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.HomeScreen.component.HomeHeaderSection
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val scrollState = rememberLazyListState()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    TudeeScaffold(
        contentBackground = Theme.color.surface,
        floatingActionButton = {
            TudeeIconButton(
                onClickIconButton = { viewModel.onAddButtonClicked() },
                isEnabled = true,
                isLoading = false,
                icon = painterResource(id = R.drawable.icon_note_add),
                contentDescription = stringResource(R.string.add_task),
                hasShadow = true,
                modifier = Modifier.size(64.dp)
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
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Theme.color.primary)
                    }
                }
                !state.hasTasks -> {
                    EmptyStateScreen(
                        onAddTask = { viewModel.onAddButtonClicked() }
                    )
                }
                else -> {
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = Theme.dimension.regular)
                    ) {
                        item {
                            HomeHeaderSection(
                                isDarkTheme = state.isDarkTheme,
                                onThemeToggle = { viewModel.onThemeToggle() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Theme.color.primary)
                            )
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(55.dp)
                                        .background(Theme.color.primary)
                                )
                                HomeOverviewCard(
                                    completedTasks = state.completedTasksCount,
                                    totalTasks = state.allTaskCount,
                                    inProgressTasks = state.inProgressTasksCount,
                                    todayDate = viewModel.getFormattedDate(),
                                    modifier = Modifier
                                        .padding(horizontal = Theme.dimension.medium)
                                )
                            }
                        }

                        if (state.hasActiveTasks && state.inProgressTasksCount > 0) {
                            stickyHeader {
                                TaskSection(
                                    title = stringResource(R.string.in_progress),
                                    taskCount = state.inProgressTasksCount,
                                    tasks = state.activeTasks.filter { it.state.name == "IN_PROGRESS" },
                                    onTaskClick = { taskId -> viewModel.onTaskClicked(taskId) },
                                    onNavigateToTaskScreen = { /* Navigate to in progress tasks */ },
                                    modifier = Modifier.background(Theme.color.surface)
                                )
                            }
                        }

                        // TODO Tasks Section
                        if (state.hasUpcomingTasks) {
                            stickyHeader {
                                TaskSection(
                                    title = stringResource(R.string.to_do),
                                    taskCount = state.todoTasksCount,
                                    tasks = state.upcomingTasks,
                                    onTaskClick = { taskId -> viewModel.onTaskClicked(taskId) },
                                    onNavigateToTaskScreen = { /* Navigate to todo tasks */ },
                                    modifier = Modifier.background(Theme.color.surface)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}


