package com.qudus.tudee.ui.screen.addTask.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.CategoryErrorType
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.TitleErrorType

@Composable
fun getCategoryErrorMessage(errorType: CategoryErrorType): String {
    return when (errorType) {
        CategoryErrorType.FAILED_IN_FETCH -> stringResource(R.string.error_category_failed_in_fetch)
        CategoryErrorType.NOT_FOUND -> stringResource(R.string.error_category_not_found)
    }
}

@Composable
fun getTitleErrorMessage(errorType: TitleErrorType): String {
    return when (errorType) {
        TitleErrorType.TOO_LONG -> stringResource(R.string.error_title_too_long)
        TitleErrorType.TOO_SHORT -> stringResource(R.string.error_title_too_short)
        TitleErrorType.INVALID_START -> stringResource(R.string.error_title_invalid_start)
        TitleErrorType.EMPTY -> stringResource(R.string.error_title_empty)
        TitleErrorType.INVALID -> stringResource(R.string.error_title_invalid)
    }
}