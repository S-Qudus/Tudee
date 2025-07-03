package com.qudus.tudee.ui.util.extension

import java.util.Locale

fun String.toLocaleDigits(locale: Locale): String =
    if (locale.language.startsWith("ar", ignoreCase = true))
        toArabicDigits()
    else this