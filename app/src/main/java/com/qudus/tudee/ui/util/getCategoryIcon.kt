package com.qudus.tudee.ui.state
package com.qudus.tudee.ui.util



import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType

fun getCategoryIcon(categoryType: DefaultCategoryType?): Int {
    return when (categoryType) {
        DefaultCategoryType.EDUCATION -> R.drawable.icon_category_book_open
        DefaultCategoryType.SHOPPING -> R.drawable.icon_calendar
        DefaultCategoryType.WORK -> R.drawable.icon_chef
        else -> R.drawable.icon_airplane
    }
}