package com.qudus.tudee.ui.util.extension

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Month

@RequiresApi(Build.VERSION_CODES.O)
fun Month.daysIn(year: Int): Int {
    return when (this) {
        Month.FEBRUARY -> year.isLeapYear().let { if (it) 29 else 28 }
        Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
        else -> 31
    }
}

fun Int.isLeapYear(): Boolean {
    return (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)
}