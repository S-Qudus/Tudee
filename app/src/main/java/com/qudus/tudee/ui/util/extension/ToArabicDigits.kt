package com.qudus.tudee.ui.util.extension

private val arabicDigits = listOf('٠','١','٢','٣','٤','٥','٦','٧','٨','٩')

fun String.toArabicDigits(): String = this.map { ch ->
    if (ch.isDigit()) arabicDigits[ch - '0'] else ch
}.joinToString("")
