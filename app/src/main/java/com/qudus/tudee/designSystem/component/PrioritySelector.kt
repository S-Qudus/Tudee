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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.data.model.Priority
import com.qudus.tudee.ui.util.getColor

@Composable
fun PrioritySelector(
    selectedPriority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Priority.entries.forEach { priority ->
            val isSelected = priority == selectedPriority
            val targetColor = if (isSelected) priority.getColor() else Color(0xFFE0E0E0)
            val contentColor = if (isSelected) Color.White else Color.Gray
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


@Preview(showBackground = true, name = "Priority Selector - Light")
@Composable
fun PreviewPrioritySelectorLight() {
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }

    MaterialTheme {
        PrioritySelector(
            selectedPriority = selectedPriority,
            onPrioritySelected = { selectedPriority = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Priority Selector - Dark")
@Composable
fun PreviewPrioritySelectorDark() {
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }

    MaterialTheme {
        PrioritySelector(
            selectedPriority = selectedPriority,
            onPrioritySelected = { selectedPriority = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
