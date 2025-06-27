package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.TaskStatusUiState
import com.qudus.tudee.ui.util.extension.toStringResource

@Composable
fun TaskStatusBadge(
    taskStatusUiState: TaskStatusUiState,
    modifier: Modifier = Modifier
) {
    val animatedBackgroundColor by animateColorAsState(targetValue = taskStatusUiState.getBackgroundColor())
    val animatedColor by animateColorAsState(targetValue = taskStatusUiState.getTextColor())
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(animatedBackgroundColor)
            .padding(vertical = 6.dp, horizontal = 12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(animatedColor),
        )
        Text(
            text = taskStatusUiState.status.toStringResource(),
            style = Theme.textStyle.label.small,
            color = animatedColor,
        )
    }
}

@Preview
@Composable
fun TaskStatusBadgePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TaskStatusBadge(TaskStatusUiState.TODO)
        TaskStatusBadge(TaskStatusUiState.IN_PROGRESS)
        TaskStatusBadge(TaskStatusUiState.DONE)
    }
}