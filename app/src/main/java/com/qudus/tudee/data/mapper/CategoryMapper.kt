package com.qudus.tudee.data.mapper

import com.qudus.tudee.data.dto.CategoryDto
import com.qudus.tudee.domain.entity.Category

fun CategoryDto.toDomain(): Category {
    return Category(
        id = this.id,
        title = this.title,
        imagePath = this.imagePath,
        isDefaultCategory = this.isDefaultCategory
    )
}

fun Category.toEntity(): CategoryDto {
    return CategoryDto(
        id = this.id,
        title = this.title,
        imagePath = this.imagePath,
        isDefaultCategory = this.isDefaultCategory
    )
}
