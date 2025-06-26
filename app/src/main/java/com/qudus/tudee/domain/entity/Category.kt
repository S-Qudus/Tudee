package com.qudus.tudee.domain.entity

data class Category(
    val id: Long,
    val title: String,
    val imagePath: String,
    val isDefaultCategory: Boolean = false
)