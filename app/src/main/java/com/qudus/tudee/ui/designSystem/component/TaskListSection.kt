package com.qudus.tudee.ui.designSystem.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.categories.CategoryUiItem
import com.qudus.tudee.ui.screen.tasksScreen.components.SwipeToRevealContainer
import com.qudus.tudee.ui.screen.tasksScreen.state.TaskUiState
import com.qudus.tudee.ui.util.getIconPainterForCategory
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListSection(
    tasks: List<TaskUiState>,
    categories: List<CategoryUiItem>,
    onClickDelete: (TaskUiState) -> Unit,
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
            items(tasks,key = {it.id}) { task ->
                val painter = CategoryIcon(task.categoryId, categories)
                Modifier.clip(RoundedCornerShape(16.dp))
                SwipeToRevealContainer(
                    item = task,
                    onDelete = {onClickDelete(it)},
                    modifier = Modifier.animateItemPlacement(),
                ) { taskItem ->
                    CategoryTask(
                        title = taskItem.title,
                        description = taskItem.description,
                        priorityLevel = taskItem.priority.toDomain(),
                        onClick = {},
                        taskRes = { modifier ->
                            Icon(
                                painter = painter,
                                contentDescription = "Task Icon",
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(
                                        top = Theme.dimension.spacing4,
                                        start = Theme.dimension.spacing4
                                    ),
                                tint = Color.Unspecified
                            )
                        }
                    )
                }

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