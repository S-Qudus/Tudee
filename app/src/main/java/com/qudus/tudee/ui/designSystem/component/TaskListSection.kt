package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.categories.CategoryUiItem
import com.qudus.tudee.ui.screen.tasksScreen.state.TaskUiState
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel
import com.qudus.tudee.ui.util.getIconPainterForCategory
import java.io.File

@Composable
fun TaskListSection(
    tasks: List<TaskUiState>,
    categories: List<CategoryUiItem>,
    modifier: Modifier = Modifier,
) {

    if (tasks.isEmpty()) {
        NoTasks(
            title = stringResource(R.string.no_tasks),
            description = stringResource(R.string.add_tasks),
            modifier = Modifier,
        )
    } else {
        LazyColumn(
            modifier = modifier
                .padding(start = Theme.dimension.spacing16, end = Theme.dimension.spacing16)
                .background(Theme.color.surface),
            verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing8),
            contentPadding = PaddingValues(top = Theme.dimension.spacing16)
        ) {
            items(tasks) { task ->
                val painter = CategoryIcon(task.categoryId, categories)
                CategoryTask(
                    title = task.title,
                    description = task.description,
                    priorityIcon = task.priority.getIcon(),
                    priorityLabel = task.priority.getLabel(),
                    priorityBackground = task.priority.getColor(),
                    onClick = {},
                    taskRes = { modifier ->
                        Icon(
                            painter = painter,
                            contentDescription = "Task Icon",
                            modifier = Modifier
                                .padding(12.dp)
                                .size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryIcon(
    categoryId: Long,
    categories: List<CategoryUiItem>
): Painter {
    val category = categories.firstOrNull { it.id == categoryId }

    return when {
        category == null -> painterResource(R.drawable.icon_category_book_open)
        category.defaultCategoryType != null -> getIconPainterForCategory(category.defaultCategoryType)
        else -> rememberAsyncImagePainter(model = File(category.imagePath))
    }
}