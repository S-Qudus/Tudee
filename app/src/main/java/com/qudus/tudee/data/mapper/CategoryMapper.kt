package com.qudus.tudee.data.mapper

import com.qudus.tudee.data.dto.CategoryDto
import com.qudus.tudee.domain.entity.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        title = title,
        imagePath = imagePath ?: "",
        defaultCategoryType = defaultCategoryType
    )
}