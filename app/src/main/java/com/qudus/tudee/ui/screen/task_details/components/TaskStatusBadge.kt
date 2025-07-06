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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.TaskStateUiState
import com.qudus.tudee.ui.state.getBackgroundColor
import com.qudus.tudee.ui.state.getStateText
import com.qudus.tudee.ui.state.getTextColor

@Composable
fun TaskStatusBadge(
    backgroundColor: Color,
    textColor: Color,
    title: String,
    modifier: Modifier = Modifier,
) {
    val animatedBackgroundColor by animateColorAsState(targetValue = backgroundColor)
    val animatedColor by animateColorAsState(targetValue = textColor)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Theme.dimension.spacing4),
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(animatedBackgroundColor)
            .padding(vertical = Theme.dimension.spacing6, horizontal = Theme.dimension.spacing12),
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(animatedColor),
        )
        Text(
            text = title,
            style = Theme.textStyle.label.small,
            color = animatedColor,
        )
    }
}

@Preview
@Composable
fun TaskStatusBadgePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing10)
    ) {
        TaskStatusBadge(
            backgroundColor = TaskStateUiState.TODO.getBackgroundColor(),
            textColor = TaskStateUiState.TODO.getTextColor(),
            title = TaskStateUiState.TODO.getStateText()
        )
        TaskStatusBadge(
            backgroundColor = TaskStateUiState.IN_PROGRESS.getBackgroundColor(),
            textColor = TaskStateUiState.IN_PROGRESS.getTextColor(),
            title = TaskStateUiState.IN_PROGRESS.getStateText()
        )
        TaskStatusBadge(
            backgroundColor = TaskStateUiState.DONE.getBackgroundColor(),
            textColor = TaskStateUiState.DONE.getTextColor(),
            title = TaskStateUiState.DONE.getStateText()
        )
    }
}