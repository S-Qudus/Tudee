package com.qudus.tudee.data.model
import com.qudus.tudee.R
enum class Priority(
    val labelResId: Int,

    val iconRes: Int
) {
    HIGH(R.string.priority_high,  R.drawable.flag),
    MEDIUM(R.string.priority_medium,  R.drawable.alert),
    LOW(R.string.priority_low,  R.drawable.trade_down)
}