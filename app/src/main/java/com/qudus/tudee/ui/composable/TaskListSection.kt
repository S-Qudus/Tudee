package com.qudus.tudee.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.CategoryTask
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TaskListSection(modifier: Modifier = Modifier, tasks: List<Task>) {

    if (tasks.isEmpty()) {
        NoTasks()
    } else {
        LazyColumn(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .background(Theme.color.surface),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp),
        ) {
            items(tasks) {
                CategoryTask(
                    title = it.title,
                    description = it.description,
                    //  priorityLevel = null,
                    onClick = {},
                    taskRes = { modifier ->
                        Icon(
                            painter = painterResource(id = R.drawable.icon_category_book_open),
                            contentDescription = "Task Icon",
                            modifier = modifier,
                            tint = Theme.color.purpleAccent
                        )
                    }
                )
            }
        }
    }
}