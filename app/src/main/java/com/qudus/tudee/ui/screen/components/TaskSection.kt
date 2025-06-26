package com.qudus.tudee.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.CategoryTask
import com.qudus.tudee.designSystem.component.PriorityLevel
import com.qudus.tudee.ui.composable.TudeeTextBadge
import com.qudus.tudee.ui.designSystem.theme.Dimension
import com.qudus.tudee.ui.designSystem.theme.Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskSection(
    title: String,
    taskCount: Int,
    tasks: List<TaskItem>,
    onTaskClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 14.dp)
            .background(Theme.color.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = Theme.textStyle.title.large,
                color = Theme.color.title
            )
            
            TudeeTextBadge(
                badgeNumber = taskCount.toString(),
                contentColor = Theme.color.onPrimary,
                containerColor = Theme.color.primary
            )
        }
        
        FlowRow(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 2,
            maxLines = 2
        ) {
            tasks.forEach { task ->
                CategoryTask(
                    title = task.title,
                    description = task.description,
                    priorityLevel = task.priority,
                    onClick = { onTaskClick(task.id) },
                    modifier = Modifier.width(320.dp),
                    taskRes = { modifier ->
                        Icon(
                            painter = painterResource(id = task.iconRes),
                            contentDescription = task.title,
                            modifier = modifier,
                            tint = task.iconTint
                        )
                    }
                )
            }
        }
    }
}

data class TaskItem(
    val id: String,
    val title: String,
    val description: String?,
    val priority: PriorityLevel,
    val iconRes: Int,
    val iconTint: Color
) 