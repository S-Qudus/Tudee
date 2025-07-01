package com.qudus.tudee.ui.screen.taskEditor

enum class TitleErrorType {
    TOO_LONG,
    INVALID_START,
    EMPTY,
    TOO_SHORT,
    INVALID
}

enum class CategoryErrorType {
    FAILED_IN_FETCH,
    NOT_FOUND
}

enum class DataErrorType {
    NOT_FOUND,
    GENERAL
}