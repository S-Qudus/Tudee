package com.qudus.tudee.designSystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.util.getColor

@Composable
fun PrioritySelector(
    selectedPriority: PriorityUiState,
    onPrioritySelected: (PriorityUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PriorityUiState.entries.forEach { priority ->
            val isSelected = priority == selectedPriority
            val targetColor = if (isSelected) priority.getColor() else  Theme.color.surfaceLow
            val contentColor = if (isSelected) Theme.color.onPrimary else Theme.color.emojiTint
            val backgroundColor by animateColorAsState(
                targetValue = targetColor,
                animationSpec = tween(durationMillis = 300),
                label = ""
            )

            PriorityItem(
                backgroundColor = backgroundColor,
                iconRes = priority.iconRes,
                labelResId = priority.labelResId,
                contentColor = contentColor,
                isSelected = isSelected,
                onClick = { onPrioritySelected(priority) }
            )
        }
    }
}

@Composable
private fun PriorityItem(
    backgroundColor: Color,
    iconRes: Int,
    labelResId: Int,
    contentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = if (isSelected) 4.dp else 1.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(labelResId),
                color = contentColor,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}




@PreviewLightDark
@Composable
fun PreviewPrioritySelector() {
    var selectedPriority by remember { mutableStateOf(PriorityUiState.MEDIUM) }

    MaterialTheme {
        PrioritySelector(
            selectedPriority = selectedPriority,
            onPrioritySelected = { selectedPriority = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

