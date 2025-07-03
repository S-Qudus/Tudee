package com.qudus.tudee.ui.exception

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.domain.exception.CategoryUpsertFailedException
import com.qudus.tudee.domain.exception.CategoryDeletionFailedException
import com.qudus.tudee.domain.exception.CategoryException
import com.qudus.tudee.domain.exception.CategoryFetchAllFailedException
import com.qudus.tudee.domain.exception.CategoryNotFoundException
import com.qudus.tudee.domain.exception.CategoryReadFailedException
import com.qudus.tudee.domain.exception.CategoryTitleMustStartWithLetterException
import com.qudus.tudee.domain.exception.CategoryTitleTooShortException
import com.qudus.tudee.domain.exception.EmptyCategoryTitleException
import com.qudus.tudee.domain.exception.EmptyTaskTitleException
import com.qudus.tudee.domain.exception.TaskUpsertFailedException
import com.qudus.tudee.domain.exception.TaskDeletionFailedException
import com.qudus.tudee.domain.exception.TaskException
import com.qudus.tudee.domain.exception.TaskFetchAllFailedException
import com.qudus.tudee.domain.exception.TaskNotFoundException
import com.qudus.tudee.domain.exception.TaskReadFailedException
import com.qudus.tudee.domain.exception.TaskStateChangeFailedException

class ExceptionHandler() {

    @Composable
    fun mapExceptionToMessage(exception: Throwable): String {
        return when (exception) {
            is TaskException -> handleTaskException(exception)
            is CategoryException -> handleCategoryException(exception)
            else -> handleUnexpectedException(exception = exception)
        }
    }

    @Composable
    private fun handleTaskException(exception: Throwable): String {
        return when (exception) {
            is TaskUpsertFailedException -> stringResource(R.string.failed_to_upsert_task)
            is TaskDeletionFailedException -> stringResource(R.string.failed_to_delete_task)
            is TaskFetchAllFailedException -> stringResource(R.string.failed_to_fetch_tasks)
            is TaskNotFoundException -> stringResource(R.string.no_tasks_found)
            is TaskReadFailedException -> stringResource(R.string.failed_to_read_task)
            is EmptyTaskTitleException -> stringResource(R.string.task_title_cannot_be_empty)
            is TaskStateChangeFailedException -> stringResource(R.string.failed_to_change_task_state)
            else -> handleUnexpectedException(stringResource(R.string.task_error), exception)
        }
    }

    @Composable
    private fun handleCategoryException(exception: Throwable): String {
        return when (exception) {
            is CategoryUpsertFailedException -> stringResource(R.string.failed_to_upsert_category)
            is CategoryDeletionFailedException -> stringResource(R.string.failed_to_delete_category)
            is CategoryFetchAllFailedException -> stringResource(R.string.failed_to_fetch_categories)
            is CategoryNotFoundException -> stringResource(R.string.no_categories_found)
            is CategoryReadFailedException -> stringResource(R.string.failed_to_read_category)
            is EmptyCategoryTitleException -> stringResource(R.string.category_title_cannot_be_empty)
            is CategoryTitleMustStartWithLetterException -> stringResource(R.string.category_title_must_start_with_letter)
            is CategoryTitleTooShortException -> stringResource(R.string.category_title_cannot_be_empty)
            else -> handleUnexpectedException(stringResource(R.string.category_error), exception)
        }
    }

    @Composable
    private fun handleUnexpectedException(
        exceptionType: String = stringResource(R.string.unexpected_error),
        exception: Throwable
    ): String {
        return "${exceptionType}: ${exception.message ?: stringResource(R.string.unexpected_error)}"
    }
}