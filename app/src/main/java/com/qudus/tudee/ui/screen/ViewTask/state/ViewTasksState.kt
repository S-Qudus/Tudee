package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import kotlinx.datetime.LocalDate

data class ViewTasksState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryTitle: String = "",
    val defaultCategoryType: DefaultCategoryType? = null,
    val selectedTab: State = State.TODO,
    val tasks: Map<State, List<TaskUiState>> = emptyMap(),
    val tasksCount: Map<State, Int> = emptyMap()
) {
    data class TaskUiState(
        val id: Long,
        val title: String,
        val description: String?,
        val priority: Priority,
        val state: State,
        val createdAt: LocalDate,
        val defaultCategoryType: DefaultCategoryType? = null

        )
}