package com.qudus.tudee.data.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.qudus.tudee.data.database.dto.CategoryDto
import com.qudus.tudee.domain.entity.DefaultCategoryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomCallBackImpl(
    private val provideDatabase: () -> TudeeDatabase
): RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {//CoroutineScope
            val database = provideDatabase()
            database.categoryDao().upsertCategories(getDefaultCategories())
        }
    }

    private fun getDefaultCategories(): List<CategoryDto> {
        return DefaultCategoryType.entries.map {
            CategoryDto(
                id = 0,
                title = it.name.replace("_", " ").lowercase(),
                imagePath = null,
                defaultCategoryType = it
            )
        }
    }
}