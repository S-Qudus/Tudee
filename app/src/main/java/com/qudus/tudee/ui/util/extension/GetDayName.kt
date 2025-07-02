package com.qudus.tudee.ui.util.extension

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import java.util.Locale
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getDayName(
    locale: Locale = Locale.getDefault(),
    short: Boolean = true
): String {
    return if (locale.language == "ar") {
        when (this.dayOfWeek) {
            DayOfWeek.MONDAY    -> "الاثنين"
            DayOfWeek.TUESDAY   -> "الثلاثاء"
            DayOfWeek.WEDNESDAY -> "الأربعاء"
            DayOfWeek.THURSDAY  -> "الخميس"
            DayOfWeek.FRIDAY    -> "الجمعة"
            DayOfWeek.SATURDAY  -> "السبت"
            DayOfWeek.SUNDAY    -> "الأحد"
        }
    } else {
        val eng = this.dayOfWeek.name
            .lowercase(locale)
            .replaceFirstChar { it.titlecase(locale) }
        if (short) eng.take(3) else eng
    }
}
