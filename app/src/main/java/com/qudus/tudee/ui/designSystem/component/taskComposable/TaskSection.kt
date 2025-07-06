package com.qudus.tudee.ui.designSystem.component.taskComposable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.ui.designSystem.component.CategoryTask
import com.qudus.tudee.ui.designSystem.component.TudeeTextBadge
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.mapper.toPriorityUiState
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskSection(
    title: String,
    taskCount: Int,
    tasks: List<Task>,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToTaskScreen: (() -> Unit)? = null
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
    modifier: Modifier = Modifier,
    onNavigateToTaskScreen: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .background(Theme.color.surface)
            .padding(horizontal = Theme.dimension.spacing16, vertical = 10.dp)
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
                    .padding(vertical = 6.dp, horizontal = Theme.dimension.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = Theme.dimension.spacing2),
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
        priorityIcon = task.priority.toPriorityUiState().getIcon(),
        priorityLabel = task.priority.toPriorityUiState().getLabel(),
        priorityBackground = task.priority.toPriorityUiState().getColor(),
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
        CATEGORY_STUDY -> R.drawable.icon_category_book_open
        CATEGORY_WORK -> R.drawable.icon_briefcase
        CATEGORY_PERSONAL -> R.drawable.icon_user
        CATEGORY_SHOPPING -> R.drawable.icon_shopping_cart
        CATEGORY_HEALTH -> R.drawable.icon_body_part_muscle
        CATEGORY_DEVELOPMENT -> R.drawable.icon_developer
        CATEGORY_FOOD -> R.drawable.icon_chef
        CATEGORY_TRAVEL -> R.drawable.icon_airplane
        CATEGORY_FINANCE -> R.drawable.icon_money_bag
        CATEGORY_NATURE -> R.drawable.icon_plant
        else -> R.drawable.icon_category_book_open
    }

    Icon(
        painter = painterResource(id = categoryIcon),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = Theme.color.primary
    )
}

private const val CATEGORY_STUDY = 1
private const val CATEGORY_WORK = 2
private const val CATEGORY_PERSONAL = 3
private const val CATEGORY_SHOPPING = 4
private const val CATEGORY_HEALTH = 5
private const val CATEGORY_DEVELOPMENT = 6
private const val CATEGORY_FOOD = 7
private const val CATEGORY_TRAVEL = 8
private const val CATEGORY_FINANCE = 9
private const val CATEGORY_NATURE = 10
