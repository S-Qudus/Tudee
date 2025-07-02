package com.qudus.tudee.ui.designSystem.component.priority

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel

@Composable
fun PriorityBadge(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: Painter,
    title: String
) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Theme.color.onPrimary,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = title,
                color = Theme.color.onPrimary,
                style = Theme.textStyle.label.small,
                modifier = Modifier.padding(start = 2.dp)

            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewHighPriorityBadge() {
    MaterialTheme {
        PriorityBadge(
            modifier = Modifier.padding(8.dp),
            backgroundColor = PriorityUiState.HIGH.getColor(),
            icon = PriorityUiState.HIGH.getIcon(),
            title = PriorityUiState.HIGH.getLabel()
        )
    }
}


