package com.qudus.tudee.ui.util.extension

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import kotlinx.datetime.*
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun DayOfWeek.toLocalizedString(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true
): String {
    return if (locale.language == "ar") {
        when (this) {
            DayOfWeek.MONDAY    -> "الاثنين"
            DayOfWeek.TUESDAY   -> "الثلاثاء"
            DayOfWeek.WEDNESDAY -> "الأربعاء"
            DayOfWeek.THURSDAY  -> "الخميس"
            DayOfWeek.FRIDAY    -> "الجمعة"
            DayOfWeek.SATURDAY  -> "السبت"
            DayOfWeek.SUNDAY    -> "الأحد"
        }
    } else {
        val eng = this.name
            .lowercase(locale)
            .replaceFirstChar { it.titlecase(locale) }
        if (short) eng.take(3) else eng
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Month.toLocalizedString(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true
): String {
    return if (locale.language == "ar") {
        when (this) {
            Month.JANUARY   -> "يناير"
            Month.FEBRUARY  -> "فبراير"
            Month.MARCH     -> "مارس"
            Month.APRIL     -> "أبريل"
            Month.MAY       -> "مايو"
            Month.JUNE      -> "يونيو"
            Month.JULY      -> "يوليو"
            Month.AUGUST    -> "أغسطس"
            Month.SEPTEMBER -> "سبتمبر"
            Month.OCTOBER   -> "أكتوبر"
            Month.NOVEMBER  -> "نوفمبر"
            Month.DECEMBER  -> "ديسمبر"
        }
    } else {
        val eng = this.name
            .lowercase(locale)
            .replaceFirstChar { it.titlecase(locale) }
        if (short) eng.take(3) else eng
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getDayName(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true
): String {
    return this.dayOfWeek.toLocalizedString(locale, short)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getMonthName(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true
): String {
    return this.month.toLocalizedString(locale, short)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatMonthToString(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true,
    withDay: Boolean = false,
): String {
    val monthName = this.month.toLocalizedString(locale, short)
    val yearPart = year.toString().toLocaleDigits(locale)
    val dayPart = if (withDay) "${dayOfMonth.toString().padStart(2, '0')} " else ""

    return "$dayPart$monthName $yearPart"
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatToFullString(
    locale: Locale = Locale.getDefault(),
    includeDayOfWeek: Boolean = true
): String {
    val dayOfWeek = if (includeDayOfWeek) "${this.dayOfWeek.toLocalizedString(locale, false)}، " else ""
    val monthName = this.month.toLocalizedString(locale, false)
    val dayPart = dayOfMonth.toString().toLocaleDigits(locale)
    val yearPart = year.toString().toLocaleDigits(locale)
    
    return "$dayOfWeek$dayPart $monthName $yearPart"
}

@RequiresApi(Build.VERSION_CODES.O)
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