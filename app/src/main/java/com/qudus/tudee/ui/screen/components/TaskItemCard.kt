package com.qudus.tudee.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.PriorityLevel
import com.qudus.tudee.designSystem.component.TudeeChip
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.datetime.*

@Composable
fun TaskItemCard(
    task: Task,
    categoryImagePath: String? = null,
    onClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Theme.dimension.medium))
            .clickable { onClick(task) }
            .background(Theme.color.surfaceHigh)
            .padding(
                start = Theme.dimension.extraSmall,
                top = Theme.dimension.extraSmall,
                end = Theme.dimension.regular
            ),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Category Thumbnail
            Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
                CategoryThumbnail(
                    imagePath = categoryImagePath,
                    modifier = Modifier.size(Theme.dimension.extraLarge)
                )
            }

            // Priority Tag and Date
            Row {
                TaskDateAndPriority(task = task)
            }
        }

        // Task Content
        Column(
            modifier = Modifier.padding(start = Theme.dimension.small),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = task.title,
                color = Theme.color.body,
                style = Theme.textStyle.label.large,
                maxLines = 1,
            )

            task.description?.let {
                Text(
                    modifier = Modifier.padding(bottom = Theme.dimension.regular),
                    text = task.description,
                    color = Theme.color.hint,
                    style = Theme.textStyle.label.small,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun CategoryThumbnail(
    imagePath: String?,
    modifier: Modifier = Modifier
) {
    // Placeholder for category image
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Theme.dimension.small))
            .background(Theme.color.primary.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_category_book_open),
            contentDescription = null,
            tint = Theme.color.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun TaskDateAndPriority(
    task: Task,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Date Chip
        TudeeChip(
            label = formatTaskDate(task.createdAt),
            icon = painterResource(id = R.drawable.icon_calendar),
            backgroundColor = Theme.color.surface,
            labelColor = Theme.color.body
        )
        
        // Priority Chip
        TudeeChip(
            label = getPriorityLabel(task.priority.toPriorityLevel()),
            icon = painterResource(id = getPriorityIcon(task.priority.toPriorityLevel())),
            backgroundColor = getPriorityColor(task.priority.toPriorityLevel()),
            labelColor = Theme.color.onPrimary
        )
    }
}

@Composable
private fun getPriorityLabel(priority: PriorityLevel): String = when (priority) {
    PriorityLevel.High -> "High"
    PriorityLevel.Medium -> "Medium"
    PriorityLevel.Low -> "Low"
}

private fun getPriorityIcon(priority: PriorityLevel): Int = when (priority) {
    PriorityLevel.High -> R.drawable.icon_priority_high
    PriorityLevel.Medium -> R.drawable.icon_priority_medium
    PriorityLevel.Low -> R.drawable.icon_priority_low
}

@Composable
private fun getPriorityColor(priority: PriorityLevel): Color = when (priority) {
    PriorityLevel.High -> Theme.color.pinkAccent
    PriorityLevel.Medium -> Theme.color.yellowAccent
    PriorityLevel.Low -> Theme.color.greenAccent
}

@Composable
private fun formatTaskDate(date: kotlinx.datetime.LocalDate): String {
    val today = kotlinx.datetime.Clock.System.now()
        .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
        .date

    return when {
        date == today -> "Today"
        date == today.minus(kotlinx.datetime.DatePeriod(days = 1)) -> "Yesterday"
        date == today.plus(kotlinx.datetime.DatePeriod(days = 1)) -> "Tomorrow"
        else -> "${date.dayOfMonth}/${date.monthNumber}/${date.year}"
    }
}