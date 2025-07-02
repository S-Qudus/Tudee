package com.qudus.tudee.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType

@Composable
fun getDefaultCategoryStringResourceByType(type: DefaultCategoryType): String {
    return when (type){
        DefaultCategoryType.EDUCATION -> stringResource(R.string.education)
        DefaultCategoryType.SHOPPING -> stringResource(R.string.shopping)
        DefaultCategoryType.MEDICAL -> stringResource(R.string.medical)
        DefaultCategoryType.GYM -> stringResource(R.string.gym)
        DefaultCategoryType.ENTERTAINMENT -> stringResource(R.string.entertainment)
        DefaultCategoryType.EVENT -> stringResource(R.string.event)
        DefaultCategoryType.WORK -> stringResource(R.string.work)
        DefaultCategoryType.BUDGETING -> stringResource(R.string.budgeting)
        DefaultCategoryType.SELF_CARE -> stringResource(R.string.self_care)
        DefaultCategoryType.ADORATION -> stringResource(R.string.adoration)
        DefaultCategoryType.FIXING_BUGS -> stringResource(R.string.fixing_bugs)
        DefaultCategoryType.CLEANING -> stringResource(R.string.cleaning)
        DefaultCategoryType.TRAVELING -> stringResource(R.string.traveling)
        DefaultCategoryType.AGRICULTURE -> stringResource(R.string.agriculture)
        DefaultCategoryType.CODING -> stringResource(R.string.coding)
        DefaultCategoryType.COOKING -> stringResource(R.string.cooking)
        DefaultCategoryType.FAMILY_AND_FRIEND -> stringResource(R.string.family_and_friend)
    }
}

@Composable
fun getIconPainterForCategory(type: DefaultCategoryType): Painter {
    return when (type) {
        DefaultCategoryType.EDUCATION -> painterResource(id = R.drawable.icon_book_open)
        DefaultCategoryType.SHOPPING -> painterResource(id = R.drawable.icon_shopping_cart)
        DefaultCategoryType.MEDICAL -> painterResource(id = R.drawable.icon_hospital_location)
        DefaultCategoryType.GYM -> painterResource(id = R.drawable.icon_body_part_muscle)
        DefaultCategoryType.ENTERTAINMENT -> painterResource(id = R.drawable.icon_baseball_bat)
        DefaultCategoryType.EVENT -> painterResource(id = R.drawable.icon_birthday_cake)
        DefaultCategoryType.WORK -> painterResource(id = R.drawable.icon_briefcase)
        DefaultCategoryType.BUDGETING -> painterResource(id = R.drawable.icon_money_bag)
        DefaultCategoryType.SELF_CARE -> painterResource(id = R.drawable.icon_in_love)
        DefaultCategoryType.ADORATION -> painterResource(id = R.drawable.icon_quran)
        DefaultCategoryType.FIXING_BUGS -> painterResource(id = R.drawable.icon_bug)
        DefaultCategoryType.CLEANING -> painterResource(id = R.drawable.icon_blush_brush)
        DefaultCategoryType.TRAVELING -> painterResource(id = R.drawable.icon_airplane)
        DefaultCategoryType.AGRICULTURE -> painterResource(id = R.drawable.icon_plant)
        DefaultCategoryType.CODING -> painterResource(id = R.drawable.icon_developer)
        DefaultCategoryType.COOKING -> painterResource(id = R.drawable.icon_chef)
        DefaultCategoryType.FAMILY_AND_FRIEND -> painterResource(id = R.drawable.icon_user_multiple)
    }
}

fun getIconResForCategory(type: DefaultCategoryType): Int {
    return when (type) {
        DefaultCategoryType.EDUCATION -> R.drawable.icon_book_open
        DefaultCategoryType.SHOPPING -> R.drawable.icon_shopping_cart
        DefaultCategoryType.MEDICAL -> R.drawable.icon_hospital_location
        DefaultCategoryType.GYM -> R.drawable.icon_body_part_muscle
        DefaultCategoryType.ENTERTAINMENT -> R.drawable.icon_baseball_bat
        DefaultCategoryType.EVENT -> R.drawable.icon_birthday_cake
        DefaultCategoryType.WORK -> R.drawable.icon_briefcase
        DefaultCategoryType.BUDGETING -> R.drawable.icon_money_bag
        DefaultCategoryType.SELF_CARE -> R.drawable.icon_in_love
        DefaultCategoryType.ADORATION -> R.drawable.icon_quran
        DefaultCategoryType.FIXING_BUGS -> R.drawable.icon_bug
        DefaultCategoryType.CLEANING -> R.drawable.icon_blush_brush
        DefaultCategoryType.TRAVELING -> R.drawable.icon_airplane
        DefaultCategoryType.AGRICULTURE -> R.drawable.icon_plant
        DefaultCategoryType.CODING -> R.drawable.icon_developer
        DefaultCategoryType.COOKING -> R.drawable.icon_chef
        DefaultCategoryType.FAMILY_AND_FRIEND -> R.drawable.icon_user_multiple
    }
}