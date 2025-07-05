package com.qudus.tudee.ui.screen.HomeScreen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.NoTasks
import com.qudus.tudee.ui.designSystem.component.SnackBar
import com.qudus.tudee.ui.designSystem.component.SnackBarState
import com.qudus.tudee.ui.designSystem.component.taskComposable.TaskSection
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiState
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    TasksContent(
        state = state,
        viewModel = viewModel,
        scrollState = scrollState,
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TasksContent(
    state: HomeUiState,
    viewModel: HomeViewModel,
    scrollState: androidx.compose.foundation.lazy.LazyListState,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            state = scrollState,
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = Theme.dimension.spacing12)
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
                OverviewCardSection(
                    state = state,
                    viewModel = viewModel
                )
            }

            if (state.hasTasks) {
                if (state.hasActiveTasks && state.inProgressTasksCount > 0) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.in_progress),
                            taskCount = state.inProgressTasksCount,
                            tasks = state.activeTasks.filter { it.state.name == "IN_PROGRESS" },
                            onTaskClick = { taskId -> viewModel.onTaskClicked(taskId) },
                            onNavigateToTaskScreen = { viewModel.onNavigateToInProgressTasks() },
                            modifier = Modifier.background(Theme.color.surface)
                        )
                    }
                }

                if (state.hasUpcomingTasks) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.to_do),
                            taskCount = state.todoTasksCount,
                            tasks = state.upcomingTasks,
                            onTaskClick = { taskId -> viewModel.onTaskClicked(taskId) },
                            onNavigateToTaskScreen = { viewModel.onNavigateToTodoTasks() },
                            modifier = Modifier.background(Theme.color.surface)
                        )
                    }
                }
            } else {
                item {
                    NoTasks()
                }
            }
        }

        var isSnackBarVisible by remember { mutableStateOf(false) }

        LaunchedEffect(state.ui.snackBarItemUiState.isVisible) {
            isSnackBarVisible = state.ui.snackBarItemUiState.isVisible
            delay(3000)
            isSnackBarVisible = false
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopCenter).padding(Theme.dimension.spacing16),
            visible = isSnackBarVisible,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            SnackBar(
                state = if (state.ui.snackBarItemUiState.operationDone) SnackBarState.SUCCESS else SnackBarState.ERROR,
                message = if (state.ui.snackBarItemUiState.operationDone) stringResource(R.string.add_task_successfully) else stringResource(
                    R.string.add_task_failed
                ),
                iconColor = if (state.ui.snackBarItemUiState.operationDone) Theme.color.greenAccent else Theme.color.error,
                background = if (state.ui.snackBarItemUiState.operationDone) Theme.color.greenVariant else Theme.color.errorVariant
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun OverviewCardSection(
    state: HomeUiState,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
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
            todayDate = viewModel.getCurrentDate(),
            modifier = Modifier
                .padding(
                    top = 9.dp,
                    start = Theme.dimension.spacing8,
                    end = Theme.dimension.spacing8
                )
        )
    }
} 