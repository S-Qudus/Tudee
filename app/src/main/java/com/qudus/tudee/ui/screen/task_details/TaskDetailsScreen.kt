package com.qudus.tudee.ui.screen.task_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.CategoryIcon
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.screen.task_details.components.TaskActionButtons
import com.qudus.tudee.ui.screen.task_details.components.TaskDetailsDivider
import com.qudus.tudee.ui.screen.task_details.components.TaskStatusAndPrioritySection
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskDetailsScreen(
    taskDetailsViewModel: TaskDetailsViewModel = koinViewModel()
) {
    val state by taskDetailsViewModel.state.collectAsState()
    TaskDetailsContent(
        state = state,
        onDismissRequest = taskDetailsViewModel::hidden
    )
}
@Composable
fun TaskDetailsContent(state: TaskDetailsUiState, onDismissRequest: () -> Unit) {
    TudeeBottomSheet(
        isSheetOpen = state.isVisible,
        onDismissRequest = onDismissRequest
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Theme.color.surface)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = R.string.task_details.toStringResource(),
                    style = Theme.textStyle.title.large,
                    color = Theme.color.title
                )
            }
            item {
                CategoryIcon(
                    iconPainter = state.taskUiState.taskCategory.image.toPainter(),
                    title = state.taskUiState.taskCategory.title,
                    isClickable = false,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 8.dp)
                        .size(56.dp)
                )
            }
            item {
                Text(
                    text = state.taskUiState.taskTitle,
                    style = Theme.textStyle.title.medium,
                    color = Theme.color.title
                )
            }
            item {
                Text(
                    text = state.taskUiState.taskDescription,
                    style = Theme.textStyle.body.small,
                    color = Theme.color.body
                )
            }
            item {
                TaskDetailsDivider()
            }
            item {
                TaskStatusAndPrioritySection(state)
            }
            item {
                TaskActionButtons()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailsContentPreview() {
    TudeeTheme(isDarkTheme = false) {
        TaskDetailsContent(
            state = TaskDetailsUiState(),
            onDismissRequest = {}
        )
    }
}