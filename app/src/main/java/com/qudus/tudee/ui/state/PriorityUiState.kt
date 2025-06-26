package com.qudus.tudee.ui.state

import com.qudus.tudee.R

enum class PriorityUiState(
    val labelResId: Int,
    val iconRes: Int,

) {
    HIGH(R.string.priority_high, R.drawable.icon_flag),
    MEDIUM(R.string.priority_medium,R.drawable.icon_alert),
    LOW(R.string.priority_low,R.drawable.icon_trade_down)
}