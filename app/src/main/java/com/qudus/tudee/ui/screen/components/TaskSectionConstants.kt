package com.qudus.tudee.ui.screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.PriorityLevel
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.designSystem.theme.Theme

object TaskSectionConstants {
    const val TASK_CARD_WIDTH = 320
    const val TASK_CARD_SPACING = 12
    const val TASK_CARD_VERTICAL_SPACING = 8
    const val MAX_ITEMS_PER_ROW = 2
    const val MAX_LINES = 2
}

// Extension functions for better organization
fun Priority.toPriorityLevel(): PriorityLevel = when (this) {
    Priority.HIGH -> PriorityLevel.High
    Priority.MEDIUM -> PriorityLevel.Medium
    Priority.LOW -> PriorityLevel.Low
}

fun State.getIconRes(): Int = when (this) {
    State.TODO -> R.drawable.icon_note_stroke
    State.IN_PROGRESS -> R.drawable.icon_loading
    State.DONE, State.COMPLETED -> R.drawable.icon_checkmark_badge
}

@Composable
fun State.getIconTint(): Color = when (this) {
    State.TODO -> Theme.color.purpleAccent
    State.IN_PROGRESS -> Theme.color.yellowAccent
    State.DONE, State.COMPLETED -> Theme.color.greenAccent
} 