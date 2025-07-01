package com.qudus.tudee.ui.screen.task_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.CategoryIcon
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.screen.task_details.components.DataErrorContent
import com.qudus.tudee.ui.screen.task_details.components.TaskActionButtons
import com.qudus.tudee.ui.screen.task_details.components.TaskDetailsDivider
import com.qudus.tudee.ui.screen.task_details.components.TaskStatusAndPrioritySection
import com.qudus.tudee.ui.state.getBackgroundColor
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel
import com.qudus.tudee.ui.state.getStatusText
import com.qudus.tudee.ui.state.getTextColor
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource
import com.qudus.tudee.ui.util.getIconResForCategory
import org.koin.androidx.compose.koinViewModel
import java.io.File

@Composable
fun TaskDetailsScreen(
    taskDetailsViewModel: TaskDetailsViewModel = koinViewModel()
) {
    val state by taskDetailsViewModel.state.collectAsState()
    TaskDetailsContent(
        state = state,
        onDismissRequest = taskDetailsViewModel::onDismiss,
        onEditTaskClick = taskDetailsViewModel::onEditTaskClick,
        onMoveTaskStatusClick = taskDetailsViewModel::onMoveTaskStatusClick,
    )
}

@Composable
fun TaskDetailsContent(
    state: TaskDetailsUiState,
    onDismissRequest: () -> Unit,
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit
) {
    TudeeBottomSheet(
        isSheetOpen = state.isVisible,
        onDismissRequest = onDismissRequest
    ) {
        state.exception?.let {
            Box(
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                DataErrorContent(
                    exception = it,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        } ?: LazyColumn(
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
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                ) {
                    Image(
                        painter = if (state.taskUiState.taskCategory.defaultCategoryType != null) {
                            getIconResForCategory(state.taskUiState.taskCategory.defaultCategoryType).toPainter()
                        } else {
                            rememberAsyncImagePainter(model = File(state.taskUiState.taskCategory.image))
                        },
                        contentDescription = state.taskUiState.taskCategory.title,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            item {
                Text(
                    text = state.taskUiState.taskTitle,
                    style = Theme.textStyle.title.medium,
                    color = Theme.color.title,
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
                TaskStatusAndPrioritySection(
                    priorityIcon = state.taskUiState.taskPriority.getIcon(),
                    priorityTitle = state.taskUiState.taskPriority.getLabel(),
                    priorityBackgroundColor = state.taskUiState.taskPriority.getColor(),
                    statusTitle = state.taskUiState.taskStatusUiState.getStatusText(),
                    statusTextColor = state.taskUiState.taskStatusUiState.getTextColor(),
                    statusBackgroundColor = state.taskUiState.taskStatusUiState.getBackgroundColor()
                )
            }
            item {
                TaskActionButtons(
                    visible = state.isTaskCompleted.not(),
                    newStatus = state.taskUiState.taskStatusUiState.getNextState().getStatusText(),
                    onEditTaskClick = onEditTaskClick,
                    onMoveTaskStatusClick = onMoveTaskStatusClick,
                    isMoveOperationLoading = state.isMoveOperationLoading,
                )
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
            onDismissRequest = {},
            onEditTaskClick = {},
            onMoveTaskStatusClick = {}
        )
    }
}