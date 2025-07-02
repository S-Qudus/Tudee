package com.qudus.tudee.ui.state

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class OverviewState(
    val todayDate: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val finishedTaskCount: Int = 0,
    val allTaskCount: Int = 0
) {
    val progressPercentage: Float
        get() = if (allTaskCount > 0) finishedTaskCount.toFloat() / allTaskCount else 0f
    
    val hasTasks: Boolean = allTaskCount > 0
} 