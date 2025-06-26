package com.qudus.tudee.ui.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayItem(
    date: LocalDate,
    isSelected: Boolean,
    onDayClick: (LocalDate) -> Unit
) {
    CalendarDay(
        date = date.dayOfMonth.toString(),
        day = date.format(DateTimeFormatter.ofPattern("EEE")),
        isSelected = isSelected,
        onClick = {
            onDayClick(date)
        }
    )
}
