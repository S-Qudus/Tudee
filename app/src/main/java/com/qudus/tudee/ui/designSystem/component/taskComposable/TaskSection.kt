package com.qudus.tudee.ui.designSystem.component.taskComposable

import androidx.compose.foundation.Image
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
import com.qudus.tudee.ui.designSystem.component.TudeeTextBadge
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.DefaultCategoryType
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.ui.util.getIconPainterForCategory
import coil.compose.rememberAsyncImagePainter
import java.io.File

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskSection(
    title: String,
    taskCount: Int,
    tasks: List<Task>,
    categories: Map<Long, com.qudus.tudee.domain.entity.Category> = emptyMap(),
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
            categories = categories,
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
    categories: Map<Long, com.qudus.tudee.domain.entity.Category>,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(TaskSectionConstants.TASK_CARD_SPACING.dp),
        verticalArrangement = Arrangement.spacedBy(TaskSectionConstants.TASK_CARD_VERTICAL_SPACING.dp),
        maxItemsInEachRow = TaskSectionConstants.MAX_ITEMS_PER_ROW
    ) {
        tasks.forEach { task ->
            TaskCard(
                task = task,
                categories = categories,
                onTaskClick = onTaskClick
            )
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    categories: Map<Long, Category>,
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
                task = task,
                categories = categories,
                contentDescription = task.title,
                modifier = iconModifier
            )
        }
    )
}

@Composable
private fun CategoryIcon(
    task: Task,
    categories: Map<Long, Category>,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val category = categories[task.categoryId]
    val imagePainter = when {
        // إذا كانت الكاتيجوري مخصصة ولديها defaultCategoryType
        category?.defaultCategoryType != null -> {
            getIconPainterForCategory(category.defaultCategoryType)
        }
        // إذا كانت الكاتيجوري مخصصة بدون defaultCategoryType، استخدم الصورة المخصصة
        category != null -> {
            rememberAsyncImagePainter(model = File(category.imagePath))
        }
        // إذا لم يتم العثور على الكاتيجوري، استخدم defaultCategoryType من المهمة
        else -> {
            when (task.defaultCategoryType) {
                DefaultCategoryType.EDUCATION -> painterResource(R.drawable.icon_category_book_open)
                DefaultCategoryType.WORK -> painterResource(R.drawable.icon_briefcase)
                DefaultCategoryType.FAMILY_AND_FRIEND -> painterResource(R.drawable.icon_user)
                DefaultCategoryType.SHOPPING -> painterResource(R.drawable.icon_shopping_cart)
                DefaultCategoryType.GYM -> painterResource(R.drawable.icon_body_part_muscle)
                DefaultCategoryType.CODING -> painterResource(R.drawable.icon_developer)
                DefaultCategoryType.COOKING -> painterResource(R.drawable.icon_chef)
                DefaultCategoryType.TRAVELING -> painterResource(R.drawable.icon_airplane)
                DefaultCategoryType.BUDGETING -> painterResource(R.drawable.icon_money_bag)
                DefaultCategoryType.AGRICULTURE -> painterResource(R.drawable.icon_plant)
                DefaultCategoryType.MEDICAL -> painterResource(R.drawable.icon_hospital_location)
                DefaultCategoryType.ENTERTAINMENT -> painterResource(R.drawable.icon_baseball_bat)
                DefaultCategoryType.EVENT -> painterResource(R.drawable.icon_birthday_cake)
                DefaultCategoryType.SELF_CARE -> painterResource(R.drawable.icon_in_love)
                DefaultCategoryType.ADORATION -> painterResource(R.drawable.icon_quran)
                DefaultCategoryType.FIXING_BUGS -> painterResource(R.drawable.icon_bug)
                DefaultCategoryType.CLEANING -> painterResource(R.drawable.icon_blush_brush)
                else -> painterResource(R.drawable.icon_category_book_open)
            }
        }
    }
    
    Image(
        painter = imagePainter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}
