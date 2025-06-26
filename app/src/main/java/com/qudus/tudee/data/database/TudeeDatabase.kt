package com.qudus.tudee.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qudus.tudee.data.database.dao.CategoryDao
import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.dto.CategoryDto
import com.qudus.tudee.data.dto.TaskDto

@Database(entities = [CategoryDto::class, TaskDto::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class TudeeDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun taskDao(): TaskDao

}