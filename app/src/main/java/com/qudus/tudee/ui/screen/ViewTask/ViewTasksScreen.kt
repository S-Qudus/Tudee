package com.qudus.tudee.ui.screen.viewTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qudus.tudee.designSystem.component.TabBar
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.screen.ViewTask.composabl.TaskItem
import com.qudus.tudee.ui.screen.ViewTask.composabl.TasksTopAppBar
import com.qudus.tudee.ui.state.StateUiState
import com.qudus.tudee.ui.state.ViewTasksState
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ViewTasksScreen(
    categoryId: Long,
    defaultCategoryType: Boolean,
    onBackClicked: () -> Unit,
    onEditCategoryClicked: () -> Unit,
    onTaskClicked: (Long) -> Unit,
    viewModel: ViewTasksViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(categoryId) {
        viewModel.loadCategoryData(categoryId)
    }

    ViewTasksContent(
        state = state,
        defaultCategoryType = defaultCategoryType,
        onBackClicked = onBackClicked,
        onEditCategoryClicked = onEditCategoryClicked,
        onTaskClicked = onTaskClicked,
        onTabSelected = { viewModel.onTabSelected(it) }
    )
}

@Composable
private fun ViewTasksContent(
    state: ViewTasksState,
    defaultCategoryType: Boolean,
    onBackClicked: () -> Unit,
    onEditCategoryClicked: () -> Unit,
    onTaskClicked: (Long) -> Unit,
    onTabSelected: (State) -> Unit
) {
    Scaffold(
        topBar = {
            TasksTopAppBar(
                title = state.categoryTitle,
                defaultCategoryType= defaultCategoryType,
                onBack = onBackClicked,
                onEditCategory = onEditCategoryClicked
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val selectedUiState = when (state.selectedTab) {
                State.TODO -> StateUiState.TODO
                State.IN_PROGRESS -> StateUiState.IN_PROGRESS
                State.DONE -> StateUiState.DONE
            }

            TabBar(
                selectedState = selectedUiState,
                countForState = state.tasksCount.mapKeys {
                    when (it.key) {
                        State.TODO -> StateUiState.TODO
                        State.IN_PROGRESS -> StateUiState.IN_PROGRESS
                        State.DONE -> StateUiState.DONE
                    }
                },
                onStateSelected = { newState ->
                    onTabSelected(
                        when (newState) {
                            StateUiState.TODO -> State.TODO
                            StateUiState.IN_PROGRESS -> State.IN_PROGRESS
                            StateUiState.DONE -> State.DONE
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            TasksList(
                tasks = state.tasks[state.selectedTab] ?: emptyList(),
                onTaskClicked = onTaskClicked
            )
        }
    }
}

@Composable
private fun TasksList(
    tasks: List<ViewTasksState.TaskUiState>,
    onTaskClicked: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                task = task,
                onTaskClicked = { onTaskClicked(task.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ViewTasksScreenPreview() {
    val mockState = ViewTasksState(
        categoryTitle = "Work",

        tasks = mapOf(
            State.TODO to listOf(
                ViewTasksState.TaskUiState(
                    id = 1,
                    title = "Complete project",
                    description = "Finish all tasks before deadline",
                    priority = Priority.HIGH,
                    state = State.TODO,
                    createdAt = LocalDate(2023, 12, 31),
                    defaultCategoryType = DefaultCategoryType.WORK
                )
            )
        ),
        tasksCount = mapOf(State.TODO to 1)
    )

    ViewTasksContent(
        state = mockState,
        defaultCategoryType = false,
        onBackClicked = {},
        onEditCategoryClicked = {},
        onTaskClicked = {},
        onTabSelected = {}
    )
}