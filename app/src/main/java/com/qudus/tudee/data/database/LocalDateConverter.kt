package com.qudus.tudee.data.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromString(value: String) = value.toLocalDate()

    @TypeConverter
    fun toString(date: LocalDate) = date.toString()
}