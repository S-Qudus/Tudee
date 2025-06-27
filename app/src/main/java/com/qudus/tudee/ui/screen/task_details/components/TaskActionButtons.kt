package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.composable.TudeeButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.task_details.TaskDetailsUiState
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource

@Composable
fun TaskActionButtons(
    state: TaskDetailsUiState,
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit
) {
    AnimatedVisibility(
        visible = state.isTaskCompleted.not(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 24.dp)
        ) {
            TudeeButton(
                isLoading = false,
                isEnabled = true,
                hasBorder = true,
                onClick = onEditTaskClick,
            ) {
                Icon(
                    painter = state.editIcon.toPainter(),
                    contentDescription = state.editIconDescription,
                    tint = Theme.color.primary
                )
            }
            TudeeButton(
                isLoading = false,
                isEnabled = true,
                hasBorder = true,
                onClick = onMoveTaskStatusClick,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${state.moveButtonTitle.toStringResource()} ${
                        state.taskUiState.taskStatusUiState.getNextState().getStatusText()
                    }",
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary
                )
            }
        }
    }
}