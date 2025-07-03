package com.qudus.tudee.ui.screen.addTask.util

import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.ui.screen.taskEditor.TitleErrorType
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType

fun getTitleErrorMessage(errorType: TitleErrorType): String {
    return when (errorType) {
        TitleErrorType.EMPTY -> "Title cannot be empty."
        TitleErrorType.INVALID_START -> "Title must start with a letter."
        TitleErrorType.TOO_LONG -> "Title must be less than 100 characters."
        TitleErrorType.TOO_SHORT -> "Title must be upper than 2 characters."
        TitleErrorType.INVALID -> "Title contains invalid characters."
    }
}

fun getCategoryErrorMessage(errorType: CategoryErrorType): String {
    return when (errorType) {
        CategoryErrorType.NOT_FOUND -> "Category not found."
        CategoryErrorType.FAILED_IN_FETCH -> "Failed to load categories. Please try again."
    }
}
//
//fun getDefaultCategoryStringResourceByType(type: DefaultCategoryType): Int {
//    return when (type) {
//        DefaultCategoryType.COOKING -> R.string.cooking
//        DefaultCategoryType.CODING -> R.string.coding
//        DefaultCategoryType.AGRICULTURE -> R.string.agriculture
//        DefaultCategoryType.TRAVELING -> R.string.traveling
//        DefaultCategoryType.CLEANING -> R.string.cleaning
//        DefaultCategoryType.FIXING_BUGS -> R.string.fixing_bugs
//        DefaultCategoryType.ADORATION -> R.string.adoration
//        DefaultCategoryType.SELF_CARE -> R.string.self_care
//        DefaultCategoryType.BUDGETING -> R.string.budgeting
//        DefaultCategoryType.WORK -> R.string.work
//        DefaultCategoryType.EVENT -> R.string.event
//        DefaultCategoryType.ENTERTAINMENT -> R.string.entertainment
//        DefaultCategoryType.GYM -> R.string.gym
//        DefaultCategoryType.MEDICAL -> R.string.medical
//        DefaultCategoryType.SHOPPING -> R.string.shopping
//        DefaultCategoryType.EDUCATION -> R.string.education
//        DefaultCategoryType.FAMILY_AND_FRIEND -> R.string.family_and_friend
//    }
//}
//
//fun getIconResForCategory(type: DefaultCategoryType): Int {
//    return when (type) {
//        DefaultCategoryType.COOKING -> R.drawable.icon_chef
//        DefaultCategoryType.CODING -> R.drawable.icon_developer
//        DefaultCategoryType.AGRICULTURE -> R.drawable.icon_plant
//        DefaultCategoryType.TRAVELING -> R.drawable.icon_airplane
//        DefaultCategoryType.CLEANING -> R.drawable.icon_blush_brush
//        DefaultCategoryType.FIXING_BUGS -> R.drawable.icon_bug
//        DefaultCategoryType.ADORATION -> R.drawable.icon_quran
//        DefaultCategoryType.SELF_CARE -> R.drawable.icon_in_love
//        DefaultCategoryType.BUDGETING -> R.drawable.icon_money_bag
//        DefaultCategoryType.WORK -> R.drawable.icon_briefcase
//        DefaultCategoryType.EVENT -> R.drawable.icon_calendar
//        DefaultCategoryType.ENTERTAINMENT -> R.drawable.icon_baseball_bat
//        DefaultCategoryType.GYM -> R.drawable.icon_body_part_muscle
//        DefaultCategoryType.MEDICAL -> R.drawable.icon_hospital_location
//        DefaultCategoryType.SHOPPING -> R.drawable.icon_shopping_cart
//        DefaultCategoryType.EDUCATION -> R.drawable.icon_book_open
//        DefaultCategoryType.FAMILY_AND_FRIEND -> R.drawable.icon_user_multiple
//    }
