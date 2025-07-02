package com.qudus.tudee.ui.state




import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType

fun getCategoryIcon(categoryType: DefaultCategoryType?): Int {
    return when (categoryType) {
        DefaultCategoryType.EDUCATION -> R.drawable.icon_category_book_open
        else -> R.drawable.icon_airplane
    }
}