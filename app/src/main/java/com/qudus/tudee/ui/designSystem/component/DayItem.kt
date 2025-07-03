package com.qudus.tudee.ui.designSystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.qudus.tudee.ui.util.extension.getDayName
import com.qudus.tudee.ui.util.extension.toLocaleDigits
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayItem(
    date: LocalDate,
    isSelected: Boolean,
    onDayClick: (LocalDate) -> Unit
) {
    val locale = LocalConfiguration.current.locale
    CalendarDay(
        date = date.dayOfMonth.toString().toLocaleDigits(locale),
        day = date.getDayName(locale),
        isSelected = isSelected,
        onClick = {
            onDayClick(date)
        }
    )
}
