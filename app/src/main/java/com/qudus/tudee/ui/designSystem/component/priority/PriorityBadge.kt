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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.PriorityUiState

@Composable
fun PriorityBadge(priority: PriorityUiState, modifier: Modifier = Modifier) {
    Surface(
        color = priority.getColor(),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = priority.getIcon(),
                contentDescription = null,
                tint = Theme.color.onPrimary,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = priority.getLabel(),
                color = Theme.color.onPrimary,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 2.dp)

            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewHighPriorityBadge() {
    MaterialTheme {
        PriorityBadge(priority = PriorityUiState.HIGH, modifier = Modifier.padding(8.dp))
    }
}


