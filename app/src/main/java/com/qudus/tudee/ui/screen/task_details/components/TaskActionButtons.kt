package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource

@Composable
fun TaskActionButtons(
    visible: Boolean,
    newStatus: String,
    onEditTaskClick: () -> Unit,
    onMoveTaskStatusClick: () -> Unit,
    isMoveOperationLoading: Boolean,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.dimension.spacing4),
        ) {
            TudeeButton(
                isLoading = false,
                isEnabled = true,
                hasBorder = true,
                onClick = onEditTaskClick,
            ) {
                Icon(
                    painter = R.drawable.icon_pencil_edit.toPainter(),
                    contentDescription = "Pencil Edit",
                    tint = Theme.color.primary
                )
            }
            TudeeButton(
                isLoading = isMoveOperationLoading,
                isEnabled = true,
                hasBorder = true,
                onClick = onMoveTaskStatusClick,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${R.string.move_to.toStringResource()} $newStatus",
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary
                )
            }
        }
    }
}