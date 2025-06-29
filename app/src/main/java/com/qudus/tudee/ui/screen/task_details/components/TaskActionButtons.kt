package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.composable.TudeeButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.task_details.TaskDetailsUiState

@Composable
fun TaskActionButtons(
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit,
    visible: Boolean,
    editButtonIcon: Painter,
    editButtonIconContentDescription: String,
    moveTaskStatusButtonTitle: String,
) {
    AnimatedVisibility(
        visible = visible,
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
                    painter = editButtonIcon,
                    contentDescription = editButtonIconContentDescription,
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
                    text = moveTaskStatusButtonTitle,
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary
                )
            }
        }
    }
}