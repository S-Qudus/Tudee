package com.qudus.tudee.ui.screen.ViewTasksByCategory

import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State

data class ViewTaskScreenState(
    val categoryId: Long? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryTitle: String = "",
    val isDefaultCategory: Boolean = false,
    val defaultCategoryType: DefaultCategoryType? = null,
    val selectedTab: State = State.TODO,
    val tasks: Map<State, List<TaskUiState>> = emptyMap(),
    val tasksCount: Map<State, Int> = emptyMap(),
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = ""
) {
    data class TaskUiState(
        val id: Long,
        val title: String,
        val description: String,
        val priority: Priority,
        val state: State,
        val createdAt: kotlinx.datetime.LocalDate,
        val defaultCategoryType: DefaultCategoryType?
    )
}