package com.qudus.tudee.ui.util.extension

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatMonthToString(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true,
    withDay: Boolean = false,
): String {

    val arMonths = listOf(
        "يناير", "فبراير", "مارس", "أبريل",
        "مايو", "يونيو", "يوليو", "أغسطس",
        "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"
    )

    val enFull = listOf(
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
    )

    val enShort = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    val idx = this.month.ordinal
    val monthName = when (locale.language) {
        "ar" -> arMonths[idx]
        else -> if (short) enShort[idx] else enFull[idx]
    }

    val yearPart = year.toString().toLocaleDigits(locale)
    val dayPart = if (withDay) "${dayOfMonth.toString().padStart(2, '0')} " else ""

    return "$dayPart$monthName $yearPart"
}
