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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.CategoryIcon
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.component.TudeeLoadingIcon
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
import com.qudus.tudee.ui.state.getStateText
import com.qudus.tudee.ui.state.getTextColor
import com.qudus.tudee.ui.util.extension.toStringResource
import com.qudus.tudee.ui.util.getIconPainterForCategory
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
        isDismissable = true,
        onDismissRequest = onDismissRequest
    ) {
        if (state.isLoading){
            TudeeLoadingIcon(modifier = Modifier.align(Alignment.CenterHorizontally), tint = Theme.color.primary)
        }else{
            state.exception?.let {
                Box(
                    modifier = Modifier.fillMaxHeight(0.5f)
                ) {
                    DataErrorContent(
                        exception = it,
                        modifier = Modifier.padding(horizontal = Theme.dimension.spacing16)
                    )
                }
            } ?: LazyColumn(
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
                        text = state.taskUiState.title,
                        style = Theme.textStyle.title.medium,
                        color = Theme.color.title,
                    )
                }
                item {
                    Text(
                        text = state.taskUiState.description,
                        style = Theme.textStyle.body.small,
                        color = Theme.color.body
                    )
                }
                item {
                    TaskDetailsDivider()
                }
                item {
                    TaskStatusAndPrioritySection(
                        priorityIcon = state.taskUiState.priority.getIcon(),
                        priorityTitle = state.taskUiState.priority.getLabel(),
                        priorityBackgroundColor = state.taskUiState.priority.getColor(),
                        statusTitle = state.taskUiState.taskState.getStateText(),
                        statusTextColor = state.taskUiState.taskState.getTextColor(),
                        statusBackgroundColor = state.taskUiState.taskState.getBackgroundColor()
                    )
                }
                item {
                    TaskActionButtons(
                        visible = state.isTaskCompleted.not(),
                        newStatus = state.taskUiState.taskState.getNextState().getStateText(),
                        onEditTaskClick = onEditTaskClick,
                        onMoveTaskStatusClick = onMoveTaskStatusClick,
                        isMoveOperationLoading = state.isMoveOperationLoading,
                    )
                }
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