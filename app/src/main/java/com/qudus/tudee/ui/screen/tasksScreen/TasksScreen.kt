package com.qudus.tudee.ui.screen.tasksScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.ui.designSystem.component.ErrorMessage
import com.qudus.tudee.ui.designSystem.component.FullScreenLoading
import androidx.navigation.NavController
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskScreen
import com.qudus.tudee.ui.screen.categories.CategoriesViewModel
import com.qudus.tudee.ui.screen.tasksScreen.viewModel.TaskViewModel
import com.qudus.tudee.ui.state.StateUiState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    categoryViewModel: CategoriesViewModel = koinViewModel(),
) {

    val tasksUiState by taskViewModel.state.collectAsState()
    val homeUiState by homeViewModel.uiState.collectAsState()
    val categoryUiState by categoryViewModel.uiState.collectAsState()

    val countsByState = remember(tasksUiState.tasks) {
        StateUiState.entries.associateWith { s ->
            tasksUiState.tasks.count { it.state == s }
        }
    }

    Scaffold(
        modifier = Modifier
            .background(Theme.color.surface)
            .statusBarsPadding(),
    ) { innerPadding ->
        when {
            tasksUiState.isLoading -> FullScreenLoading()

            tasksUiState.error != null -> ErrorMessage(tasksUiState.error.toString())

            else -> TasksScreenContent(
                modifier = Modifier.padding(innerPadding),
                uiState = tasksUiState,
                countsByState = countsByState,
                onDateSelected = { taskViewModel.selectDate(it) },
                onMonthChange = { taskViewModel.selectMonth(it) },
                onStateSelected = { taskViewModel.selectState(it) },
                onClickAddNewTask = { homeViewModel.onAddButtonClicked() },
                categoriesUiState = categoryUiState
            )
        }

        if (homeUiState.showAddTaskSheet) {
            AddTaskScreen(
                onDismiss = { homeViewModel.onDismissBottomSheet() },
                onTaskAdded = { homeViewModel.refreshTasks() },
                navController = navController
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview()
//@PreviewLightDark()
@Composable
private fun TasksScreenPreview() {
//    TasksScreen()
}