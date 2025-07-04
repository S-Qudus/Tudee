package com.qudus.tudee.ui.screen.ViewTasksByCategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.designSystem.component.CategoryTask
import com.qudus.tudee.ui.designSystem.component.TabBar
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.ViewTasksByCategory.composabl.TasksTopAppBar
import com.qudus.tudee.ui.state.StateUiState
import com.qudus.tudee.ui.state.getCategoryIcon
import kotlinx.datetime.LocalDate

@Composable
fun ViewTaskScreen(
    categoryId: Long,
    onBackClick: () -> Unit,
    onEditCategory: (Long) -> Unit,
    viewModel: ViewTaskViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(categoryId) {
        viewModel.loadCategoryData(categoryId)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is ViewTaskEvents.NavigateBack -> onBackClick()
                is ViewTaskEvents.NavigateToEditCategory -> onEditCategory(event.categoryId)
            }
        }
    }

    Scaffold(
        topBar = {
            TasksTopAppBar(
                title = state.categoryTitle,
                isDefaultCategory = state.isDefaultCategory,
                onBack = onBackClick,
                onEditCategory = { viewModel.editCategory() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Theme.color.surface)
        ) {
            when {
                state.isLoading -> LoadingView()
                state.error != null -> ErrorView(onRetry = { viewModel.loadCategoryData(categoryId) })
                state.tasks.isEmpty() -> EmptyTasksView()
                else -> TasksContent(state, viewModel)
            }
        }
    }
}

@Composable
private fun TasksContent(
    state: ViewTaskScreenState,
    actions: ViewTasksInteraction
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TabBar(
            selectedState = state.selectedTab.toStateUiState(),
            countForState = state.tasksCount.mapKeys { it.key.toStateUiState() },
            onStateSelected = { tab -> actions.selectTab(tab.toDomainState()) },
            modifier = Modifier.fillMaxWidth()
        )

        state.tasks[state.selectedTab]?.let { tasks ->
            TasksList(tasks = tasks)
        } ?: EmptyTabView()
    }
}

private fun State.toStateUiState(): StateUiState = when (this) {
    State.TODO -> StateUiState.TODO
    State.IN_PROGRESS -> StateUiState.IN_PROGRESS
    State.DONE -> StateUiState.DONE
}

private fun StateUiState.toDomainState(): State = when (this) {
    StateUiState.TODO -> State.TODO
    StateUiState.IN_PROGRESS -> State.IN_PROGRESS
    StateUiState.DONE -> State.DONE
}

@Composable
private fun TasksList(
    tasks: List<ViewTaskScreenState.TaskUiState>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
private fun TaskItem(
    task: ViewTaskScreenState.TaskUiState
) {
    val iconRes = task.defaultCategoryType?.let { getCategoryIcon(it) }
        ?: R.drawable.icon_bug

    CategoryTask(
        title = task.title,
        description = task.description,
        priorityLevel = task.priority,
        dateText = task.createdAt.toString(),
        taskRes = { modifier ->
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Task Icon",
                modifier = modifier,
                tint = Theme.color.purpleAccent
            )
        },
        onClick = {}
    )
}

@Composable
private fun EmptyTasksView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.icon_calendar),
            contentDescription = "No tasks",
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
private fun EmptyTabView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No tasks in this category", color = Theme.color.hint)
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Theme.color.primary)
    }
}

@Composable
private fun ErrorView(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_alert),
            contentDescription = "Error",
            tint = Theme.color.error,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = "Failed to load tasks",
            color = Theme.color.error,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun LoadingStatePreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        LoadingView()
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun ErrorStatePreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        ErrorView(onRetry = {})
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
fun EmptyStatePreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        EmptyTasksView()
    }
}

@Preview(showBackground = true, name = "With Tasks")
@Composable
fun WithTasksPreview() {
    val sampleTasks = listOf(
        ViewTaskScreenState.TaskUiState(
            id = 1L,
            title = "Complete project",
            description = "Finish all tasks before deadline",
            priority = Priority.HIGH,
            state = State.TODO,
            createdAt = LocalDate(2023, 12, 31),
            defaultCategoryType = DefaultCategoryType.WORK
        ),
        ViewTaskScreenState.TaskUiState(
            id = 2L,
            title = "Code review",
            description = "Review team member's code",
            priority = Priority.MEDIUM,
            state = State.TODO,
            createdAt = LocalDate(2023, 12, 30),
            defaultCategoryType = DefaultCategoryType.WORK
        )
    )

    val sampleState = ViewTaskScreenState(
        categoryTitle = "Work Tasks",
        isDefaultCategory = false,
        selectedTab = State.TODO,
        tasks = mapOf(
            State.TODO to sampleTasks,
            State.IN_PROGRESS to emptyList(),
            State.DONE to emptyList()
        ),
        tasksCount = mapOf(
            State.TODO to sampleTasks.size,
            State.IN_PROGRESS to 0,
            State.DONE to 0
        )
    )

    Scaffold(
        topBar = {
            TasksTopAppBar(
                title = sampleState.categoryTitle,
                isDefaultCategory = sampleState.isDefaultCategory,
                onBack = {},
                onEditCategory = {}
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabBar(
                selectedState = sampleState.selectedTab.toStateUiState(),
                countForState = sampleState.tasksCount.mapKeys { it.key.toStateUiState() },
                onStateSelected = {},
                modifier = Modifier.fillMaxWidth()
            )
            TasksList(tasks = sampleTasks)
        }
    }
}

@Preview(showBackground = true, name = "Empty Tab")
@Composable
fun EmptyTabPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        EmptyTabView()
    }
}