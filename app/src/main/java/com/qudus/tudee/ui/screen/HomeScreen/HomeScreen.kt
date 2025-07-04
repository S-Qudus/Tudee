package com.qudus.tudee.ui.screen.HomeScreen

import MessageState
import MessageType
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeFloatingActionButton
import com.qudus.tudee.ui.designSystem.component.TudeeScaffold
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.HomeScreen.component.HomeContent
import com.qudus.tudee.ui.screen.addTask.AddTaskScreen
import com.qudus.tudee.ui.screen.editTask.EditTaskScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var currentMessage by remember { mutableStateOf<MessageState?>(null) }
    
    TudeeScaffold(
        contentBackground = Theme.color.surface,
        snackbarHostState = snackbarHostState,
        floatingActionButton = {
            TudeeFloatingActionButton(
                onClickIconButton = { viewModel.onAddButtonClicked() },
                isEnabled = true,
                isLoading = false,
                contentDescription = stringResource(R.string.add_task),
                hasShadow = true,
                painter = painterResource(id = R.drawable.icon_note_add),
                modifier = Modifier.size(64.dp)
            )
        },
        content = {
            HomeContent(
                viewModel = viewModel
            )
        }
    )
    
    LaunchedEffect(currentMessage) {
        currentMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message.text,
                duration = when (message.type) {
                    MessageType.SUCCESS -> SnackbarDuration.Short
                    MessageType.ERROR -> SnackbarDuration.Long
                }
            )
            currentMessage = null
        }
    }
    
    if (state.showAddTaskSheet) {
        AddTaskScreen(
            onDismiss = { viewModel.onDismissBottomSheet() },
            onTaskAdded = { viewModel.refreshTasks() },
            onShowMessage = { message -> currentMessage = message },
            navController = navController
        )
    }
    
    if (state.showEditTaskSheet) {
        state.selectedTaskId?.let { taskId ->
            EditTaskScreen(
                taskId = taskId,
                onDismiss = { viewModel.onDismissEditTaskSheet() },
                onTaskEdited = { viewModel.refreshTasks() },
                onShowMessage = { message -> currentMessage = message }
            )
        }
    }
}


