package com.qudus.tudee.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import kotlinx.datetime.LocalDate

@Composable
fun LocalDate.formatToArabicString(): String {
    val dayOfWeek = when (this.dayOfWeek.name) {
        "MONDAY" -> stringResource(R.string.monday)
        "TUESDAY" -> stringResource(R.string.tuesday)
        "WEDNESDAY" -> stringResource(R.string.wednesday)
        "THURSDAY" -> stringResource(R.string.thursday)
        "FRIDAY" -> stringResource(R.string.friday)
        "SATURDAY" -> stringResource(R.string.saturday)
        "SUNDAY" -> stringResource(R.string.sunday)
        else -> this.dayOfWeek.name.take(3)
    }
    
    val month = when (this.month.name) {
        "JANUARY" -> stringResource(R.string.january)
        "FEBRUARY" -> stringResource(R.string.february)
        "MARCH" -> stringResource(R.string.march)
        "APRIL" -> stringResource(R.string.april)
        "MAY" -> stringResource(R.string.may)
        "JUNE" -> stringResource(R.string.june)
        "JULY" -> stringResource(R.string.july)
        "AUGUST" -> stringResource(R.string.august)
        "SEPTEMBER" -> stringResource(R.string.september)
        "OCTOBER" -> stringResource(R.string.october)
        "NOVEMBER" -> stringResource(R.string.november)
        "DECEMBER" -> stringResource(R.string.december)
        else -> this.month.name.take(3)
    }
    
    return "$dayOfWeek، ${this.dayOfMonth} $month ${this.year}"
} 