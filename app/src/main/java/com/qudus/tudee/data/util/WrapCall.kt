package com.qudus.tudee.data.util

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.qudus.tudee.domain.exception.CategoryDeletionFailedException
import com.qudus.tudee.domain.exception.CategoryNotFoundException
import com.qudus.tudee.domain.exception.CategoryUpsertFailedException
import com.qudus.tudee.domain.exception.TaskDeletionFailedException
import com.qudus.tudee.domain.exception.TaskNotFoundException
import com.qudus.tudee.domain.exception.TaskUpsertFailedException
import com.qudus.tudee.domain.exception.TudeeExecption

private fun mapRoomException(isCategory: Boolean, exception: Throwable): TudeeExecption {
    return when (exception) {
        is SQLiteConstraintException -> if (isCategory) CategoryUpsertFailedException() else TaskUpsertFailedException()
        is IllegalStateException -> if (isCategory) CategoryNotFoundException() else TaskNotFoundException()
        is SQLiteException -> if (isCategory) CategoryDeletionFailedException() else TaskDeletionFailedException()
        else -> TudeeExecption(exception.message ?: "Unknown error occurred")
    }
}

suspend fun <T> wrapServiceSuspendCall(
    isCategory: Boolean = false,
    call: suspend () -> T
): T {
    return try {
        call()
    } catch (exception: Exception) {
        throw mapRoomException(isCategory, exception)
    }
}

fun <T> wrapServiceCall(
    isCategory: Boolean = false,
    call: () -> T
): T {
    return try {
        call()
    } catch (exception: Exception) {
        throw mapRoomException(isCategory, exception)
    }
}

