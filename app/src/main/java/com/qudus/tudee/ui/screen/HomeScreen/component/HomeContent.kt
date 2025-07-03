package com.qudus.tudee.ui.screen.HomeScreen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiState
import com.qudus.tudee.ui.composable.taskComposable.TaskSection
import com.qudus.tudee.ui.composable.NoTasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

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
                .padding(top = 9.dp, start = Theme.dimension.spacing8, end = Theme.dimension.spacing8)
        )
    }
} 