package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TaskTabItem(tab: TaskStatus, isSelected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = tab.title,
            textAlign = TextAlign.Center,
            style = if (isSelected)
                Theme.textStyle.label.medium else Theme.textStyle.label.small,
        )
        if (tab.count > 0 && isSelected) {
            Box(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(26.dp)
                    .background(Theme.color.surface, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab.count.toString(),
                    style = Theme.textStyle.label.medium,
                    color = Theme.color.body
                )
            }
        }
    }
}
