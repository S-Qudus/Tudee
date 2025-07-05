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
import com.qudus.tudee.ui.util.getIconPainterForCategory
import java.io.File

@Composable
fun TaskListSection(
    modifier: Modifier = Modifier,
    tasks: List<TaskUiState>,
    categories: List<CategoryUiItem>,
) {

    if (tasks.isEmpty()) {
        NoTasks(
            title = stringResource(R.string.no_tasks),
            description = stringResource(R.string.add_tasks),
        )
    } else {
        LazyColumn(
            modifier = modifier
                .padding(start = Theme.dimension.spacing16, end = Theme.dimension.spacing16)
                .background(Theme.color.surface),
            verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing8),
           contentPadding = PaddingValues(top= Theme.dimension.spacing16)
        ) {
            items(tasks) { task ->
                val painter = CategoryIcon(task.categoryId, categories)
                CategoryTask(
                    title = task.title,
                    description = task.description,
                    priorityLevel = task.priority.toDomain(),
                    onClick = {},
                    taskRes = { modifier ->
                        Icon(
                            painter = painter,
                            contentDescription = "Task Icon",
                            modifier = Modifier.size(32.dp).padding(top = 4.dp, start = 4.dp),
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