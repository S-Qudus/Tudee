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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: EditTaskViewModel = koinViewModel<EditTaskViewModel>()
) {
    val state by viewModel.state.collectAsState()

    TaskScreenContent(
        modifier = modifier,
        state = state,
        interaction = viewModel,
        onPrimaryActionClick = viewModel::onEditTaskClicked
    )
}