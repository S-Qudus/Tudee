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

suspend fun <T> wrapServiceSuspendCall(
    isCategory: Boolean = false,
    call: suspend () -> T
): T {
    return try {
        call()
    } catch (_: SQLiteConstraintException) {
        if (isCategory) {
            throw CategoryUpsertFailedException()
        } else {
            throw TaskUpsertFailedException()
        }
    } catch (_: IllegalStateException) {
        if (isCategory) {
            throw CategoryNotFoundException()
        } else {
            throw TaskNotFoundException()
        }
    } catch (_: SQLiteException) {
        if (isCategory) {
            throw CategoryDeletionFailedException()
        } else {
            throw TaskDeletionFailedException()
        }
    } catch (_: Exception) {
        throw TudeeExecption()
    }
}

fun <T> wrapServiceCall(
    isCategory: Boolean = false,
    call:() -> T
): T {
    return try {
        call()
    } catch (_: SQLiteConstraintException) {
        if (isCategory) {
            throw CategoryUpsertFailedException()
        } else {
            throw TaskUpsertFailedException()
        }
    } catch (_: IllegalStateException) {
        if (isCategory) {
            throw CategoryNotFoundException()
        } else {
            throw TaskNotFoundException()
        }
    } catch (_: SQLiteException) {
        if (isCategory) {
            throw CategoryDeletionFailedException()
        } else {
            throw TaskDeletionFailedException()
        }
    } catch (_: Exception) {
        throw TudeeExecption()
    }
}

