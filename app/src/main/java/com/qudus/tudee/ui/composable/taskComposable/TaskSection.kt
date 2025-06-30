package com.qudus.tudee.ui.composable.taskComposable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.CategoryTask
import com.qudus.tudee.ui.composable.TudeeTextBadge
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.domain.entity.Task
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskSection(
    title: String,
    taskCount: Int,
    tasks: List<Task>,
    onTaskClick: (Long) -> Unit,
    onNavigateToTaskScreen: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) return
    
    Column(
        modifier = modifier
            .background(Theme.color.surface)
    ) {
        TaskSectionHeader(
            title = title,
            taskCount = taskCount,
            onNavigateToTaskScreen = onNavigateToTaskScreen
        )
        TaskSectionContent(
            tasks = tasks,
            onTaskClick = onTaskClick
        )
    }
}

@Composable
private fun TaskSectionHeader(
    title: String,
    taskCount: Int,
    onNavigateToTaskScreen: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Theme.color.surface)
            .padding(horizontal = Theme.dimension.medium, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = Theme.color.title,
            style = Theme.textStyle.title.large
        )

        if (onNavigateToTaskScreen != null) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .clickable { onNavigateToTaskScreen() }
                    .background(Theme.color.surfaceHigh)
                    .padding(vertical = 6.dp, horizontal = Theme.dimension.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 2.dp),
                    text = taskCount.toString(),
                    color = Theme.color.body,
                    style = Theme.textStyle.label.small
                )

                Icon(
                    painter = painterResource(R.drawable.arrow_icon),
                    tint = Theme.color.body,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        } else {
            TudeeTextBadge(
                badgeNumber = taskCount.toString(),
                contentColor = Theme.color.onPrimary,
                containerColor = Theme.color.primary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TaskSectionContent(
    tasks: List<Task>,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(TaskSectionConstants.TASK_CARD_SPACING.dp),
        verticalArrangement = Arrangement.spacedBy(TaskSectionConstants.TASK_CARD_VERTICAL_SPACING.dp),
        maxItemsInEachRow = TaskSectionConstants.MAX_ITEMS_PER_ROW,
        maxLines = TaskSectionConstants.MAX_LINES
    ) {
        tasks.forEach { task ->
            TaskCard(
                task = task,
                onTaskClick = onTaskClick
            )
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    CategoryTask(
        title = task.title,
        description = task.description,
        priorityLevel = task.priority,
        onClick = { onTaskClick(task.id) },
        modifier = modifier.width(TaskSectionConstants.TASK_CARD_WIDTH.dp),
        taskRes = { iconModifier ->
            CategoryIcon(
                categoryId = task.categoryId,
                contentDescription = task.title,
                modifier = iconModifier
            )
        }
    )
}

@Composable
private fun CategoryIcon(
    categoryId: Long,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val categoryIcon = when (categoryId.toInt()) {
        1 -> R.drawable.icon_category_book_open    // Study/Book
        2 -> R.drawable.icon_briefcase             // Work
        3 -> R.drawable.icon_user                  // Personal
        4 -> R.drawable.icon_shopping_cart         // Shopping
        5 -> R.drawable.icon_body_part_muscle      // Health/Fitness
        6 -> R.drawable.icon_developer             // Development/Programming
        7 -> R.drawable.icon_chef                  // Food/Cooking
        8 -> R.drawable.icon_airplane              // Travel
        9 -> R.drawable.icon_money_bag             // Finance
        10 -> R.drawable.icon_plant                // Nature/Environment
        else -> R.drawable.icon_category_book_open // Default
    }
    
    Icon(
        painter = painterResource(id = categoryIcon),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = Theme.color.primary
    )
}
