package com.qudus.tudee.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.domain.entity.Task

@Composable
fun TaskSection(
    title: String,
    taskCount: Int,
    tasks: List<Task>,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) return
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.dimension.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = Theme.textStyle.title.medium,
                color = Theme.color.title
            )
            Text(
                text = taskCount.toString(),
                style = Theme.textStyle.body.medium,
                color = Theme.color.body
            )
        }
        
        Spacer(modifier = Modifier.height(Theme.dimension.small))
        
        tasks.forEach { task ->
            TaskItem(
                task = task,
                onClick = { onTaskClick(task.id) }
            )
            Spacer(modifier = Modifier.height(Theme.dimension.small))
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // هنا يمكن إضافة تصميم TaskItem
    // حالياً نعرض النص فقط للتوضيح
    Text(
        text = "${task.title} - ${task.state.name}",
        style = Theme.textStyle.body.medium,
        color = Theme.color.body,
        modifier = modifier
    )
} 