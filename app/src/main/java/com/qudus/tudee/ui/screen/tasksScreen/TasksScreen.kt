package com.qudus.tudee.ui.screen.tasksScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
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
import com.qudus.tudee.ui.screen.tasksScreen.viewModel.TaskViewModel
import com.qudus.tudee.ui.state.StateUiState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TaskViewModel = koinViewModel()
) {

    val uiState by viewModel.state.collectAsState()

    val countsByState = remember(uiState.tasks) {
        StateUiState.entries.associateWith { s ->
            uiState.tasks.count { it.state == s }
        }
    }

    Scaffold(
        modifier = Modifier
            .background(Theme.color.surface)
    ) { innerPadding ->
        when {
            uiState.isLoading -> FullScreenLoading()

            uiState.error != null -> ErrorMessage(uiState.error.toString())

            else -> TasksScreenContent(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
                countsByState = countsByState,
                onDateSelected = { viewModel.selectDate(it) },
                onMonthChange = { viewModel.selectMonth(it) },
                onStateSelected = { viewModel.selectState(it) }
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