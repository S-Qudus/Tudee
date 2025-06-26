package com.qudus.tudee.ui.screen.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.PriorityLevel
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.components.TaskItem

@Composable
fun getInProgressTasks(): List<TaskItem> = listOf(
    TaskItem(
        id = "1",
        title = "Organize Study Desk",
        description = "Clean up workspace and arrange study materials for better productivity",
        priority = PriorityLevel.High,
        iconRes = R.drawable.icon_category_book_open,
        iconTint = Theme.color.purpleAccent
    ),
    TaskItem(
        id = "2",
        title = "Review Project Documentation",
        description = "Go through the latest project requirements and update documentation",
        priority = PriorityLevel.Medium,
        iconRes = R.drawable.icon_developer,
        iconTint = Theme.color.yellowAccent
    ),
    TaskItem(
        id = "3",
        title = "Prepare Weekly Report",
        description = "Compile progress data and create comprehensive weekly summary",
        priority = PriorityLevel.Low,
        iconRes = R.drawable.icon_briefcase,
        iconTint = Theme.color.greenAccent
    )
)

@Composable
fun getToDoTasks(): List<TaskItem> = listOf(
    TaskItem(
        id = "4",
        title = "Schedule Team Meeting",
        description = "Coordinate with team members for the upcoming sprint planning",
        priority = PriorityLevel.High,
        iconRes = R.drawable.icon_calendar,
        iconTint = Theme.color.pinkAccent
    ),
    TaskItem(
        id = "5",
        title = "Update Mobile App",
        description = "Implement the latest UI improvements and bug fixes",
        priority = PriorityLevel.High,
        iconRes = R.drawable.icon_developer,
        iconTint = Theme.color.yellowAccent
    ),
    TaskItem(
        id = "6",
        title = "Grocery Shopping",
        description = "Buy ingredients for this week's meal preparation",
        priority = PriorityLevel.Medium,
        iconRes = R.drawable.icon_shopping_cart,
        iconTint = Theme.color.greenAccent
    ),
    TaskItem(
        id = "7",
        title = "Exercise Session",
        description = "Complete 30-minute cardio and strength training routine",
        priority = PriorityLevel.Medium,
        iconRes = R.drawable.icon_body_part_muscle,
        iconTint = Theme.color.greenAccent
    ),
    TaskItem(
        id = "8",
        title = "Read Research Paper",
        description = "Review the latest findings in machine learning algorithms",
        priority = PriorityLevel.Low,
        iconRes = R.drawable.icon_category_book_open,
        iconTint = Theme.color.purpleAccent
    )
) 