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
import com.qudus.tudee.ui.composable.taskComposable.TaskSection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        !state.hasTasks -> {
            Text("This text temp: just we need NoTask composble")
        }
        else -> {
            TasksContent(
                state = state,
                viewModel = viewModel,
                scrollState = scrollState,
                modifier = modifier
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TasksContent(
    state: com.qudus.tudee.ui.state.HomeUiState,
    viewModel: HomeViewModel,
    scrollState: androidx.compose.foundation.lazy.LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize(),
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
            OverviewCardSection(
                state = state,
                viewModel = viewModel
            )
        }

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
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun OverviewCardSection(
    state: com.qudus.tudee.ui.state.HomeUiState,
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
            todayDate = viewModel.getFormattedDate(),
            modifier = Modifier
                .padding(horizontal = Theme.dimension.medium)
        )
    }
} 