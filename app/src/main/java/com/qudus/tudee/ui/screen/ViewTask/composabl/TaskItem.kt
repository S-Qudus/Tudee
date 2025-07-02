package com.qudus.tudee.ui.screen.ViewTask.composabl



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.CategoryTask
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.ViewTasksState
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.util.getCategoryIcon
import kotlinx.datetime.LocalDate


@Composable
fun TaskItem(
    task: ViewTasksState.TaskUiState,
    onTaskClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconRes = if (task.defaultCategoryType != null) {
        getCategoryIcon(task.defaultCategoryType)
    } else {
        R.drawable.icon_bug
    }

    CategoryTask(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        title = task.title,
        description = task.description,
        priorityLevel = task.priority,
        onClick = onTaskClicked,
        dateText = task.createdAt.toString(),
        taskRes = { modifier ->
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Task Icon",
                modifier = modifier,
                tint = Theme.color.purpleAccent
            )
        }
    )
}
@Preview
@Composable
private fun TaskItemPreview() {
    TaskItem(
        task = ViewTasksState.TaskUiState(
            id = 1,
            title = "Complete project",
            description = "Finish all tasks before deadline",
            priority = Priority.HIGH,
            state = State.IN_PROGRESS,
            createdAt = LocalDate(2023, 12, 31), // استخدام التاريخ الحالي
            defaultCategoryType = DefaultCategoryType.WORK //



        ),
        onTaskClicked = {}
    )
}