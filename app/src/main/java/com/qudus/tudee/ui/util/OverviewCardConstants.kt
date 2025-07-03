package com.qudus.tudee.ui.util


object OverviewCardConstants {
    const val CARD_OFFSET_Y = -40
    const val ROBOT_WIDTH = 76
    const val ROBOT_HEIGHT = 92
    const val ROBOT_BACKGROUND_SIZE = 64.75
    const val ROBOT_IMAGE_WIDTH = 61
    const val ROBOT_IMAGE_HEIGHT = 92
    const val EMOJI_SIZE = 20
    const val MESSAGE_WIDTH_RATIO = 0.8f
    
    // Status thresholds
    const val EXCELLENT_THRESHOLD = 0.7
    const val GOOD_THRESHOLD = 0.4
}

enum class UserStatus {
    GOOD,
    OKAY,
    POOR,
    BAD
}

fun calculateUserStatus(completedTasks: Int, totalTasks: Int): UserStatus = when {
    totalTasks == 0 -> UserStatus.POOR
    completedTasks == totalTasks -> UserStatus.GOOD
    completedTasks >= totalTasks * OverviewCardConstants.EXCELLENT_THRESHOLD -> UserStatus.GOOD
    completedTasks >= totalTasks * OverviewCardConstants.GOOD_THRESHOLD -> UserStatus.OKAY
    completedTasks > 0 -> UserStatus.POOR
    else -> UserStatus.BAD
}

fun UserStatus.getEmoji(): String = when (this) {
    UserStatus.GOOD -> "🎉"
    UserStatus.OKAY -> "😃"
    UserStatus.POOR -> "🤔"
    UserStatus.BAD -> "😤"
} 