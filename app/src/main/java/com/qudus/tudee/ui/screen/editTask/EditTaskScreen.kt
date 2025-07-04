package com.qudus.tudee.ui.screen.editTask

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.qudus.tudee.ui.screen.taskEditor.TaskScreenContent
import org.koin.androidx.compose.koinViewModel
import MessageState
import org.koin.core.parameter.parametersOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    taskId: Long,
    onDismiss: () -> Unit,
    onTaskEdited: () -> Unit,
    onShowMessage: (MessageState) -> Unit
) {
    val viewModel: EditTaskViewModel = koinViewModel { parametersOf(taskId, onDismiss, onTaskEdited, onShowMessage) }
    val state by viewModel.state.collectAsState()

    TaskScreenContent(
        state = state,
        interaction = viewModel,
        onPrimaryActionClick = viewModel::onEditTaskClicked
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntry
    val taskId = backStackEntry?.savedStateHandle?.let { 
        EditTaskArgs(it).taskId 
    } ?: return

    val viewModel: EditTaskViewModel = koinViewModel { 
        parametersOf(
            taskId, 
            { navController.popBackStack() },
            { navController.popBackStack() },
            { /* يمكن إضافة منطق لعرض الرسائل */ }
        ) 
    }
    val state by viewModel.state.collectAsState()

    TaskScreenContent(
        modifier = modifier,
        state = state,
        interaction = viewModel,
        onPrimaryActionClick = viewModel::onEditTaskClicked
    )
}