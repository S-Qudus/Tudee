package com.qudus.tudee.ui.screen.task_details

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
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.designSystem.component.CategoryIcon
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
import com.qudus.tudee.ui.util.extension.toStringResource
import com.qudus.tudee.ui.util.getIconPainterForCategory
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File

@Composable
fun TaskDetailsScreen(
    taskId: Long,
    onDismiss: () -> Unit,
    onEditTaskClick: () -> Unit,
    taskDetailsViewModel: TaskDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            taskId,
            onDismiss
        )
    })
) {
    val state by taskDetailsViewModel.state.collectAsState()
    TudeeBottomSheet(
        isSheetOpen = state.isVisible,
        onDismissRequest = onDismiss
    ) {
        TaskDetailsContent(
            state = state,
            onEditTaskClick = onEditTaskClick,
            onMoveTaskStatusClick = taskDetailsViewModel::onMoveTaskStatusClick,
        )
    }
}

@Composable
private fun TaskDetailsContent(
    state: TaskDetailsUiState,
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit
) {
    state.exception?.let {
        TaskDetailsErrorContent(it)
    } ?: TaskDetailsSuccessContent(state, onEditTaskClick, onMoveTaskStatusClick)
}

@Composable
private fun TaskDetailsSuccessContent(
    state: TaskDetailsUiState,
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(Theme.color.surface)
            .padding(horizontal = Theme.dimension.spacing16)
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
                modifier = Modifier.padding(
                    top = Theme.dimension.spacing12,
                    bottom = Theme.dimension.spacing8
                ),
                imagePainter = if (state.categoryUiState.defaultCategoryType != null) {
                    getIconPainterForCategory(state.categoryUiState.defaultCategoryType)
                } else {
                    rememberAsyncImagePainter(model = File(state.categoryUiState.image))
                },
                title = state.categoryUiState.title,
            )
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

@Composable
private fun TaskDetailsErrorContent(exception: TudeeExecption) {
    Box(
        modifier = Modifier.fillMaxHeight(0.5f)
    ) {
        DataErrorContent(
            exception = exception,
            modifier = Modifier.padding(horizontal = Theme.dimension.spacing16)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailsContentPreview() {
    TudeeTheme(isDarkTheme = false) {
        TaskDetailsContent(
            state = TaskDetailsUiState(),
            onEditTaskClick = {},
            onMoveTaskStatusClick = {}
        )
    }
}