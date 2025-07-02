package com.qudus.tudee.ui.screen.viewTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.state.ViewTasksState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewTasksViewModel(
    private val taskService: TaskService,
    private val categoryService: CategoryService
) : ViewModel() {

    private val _state = MutableStateFlow(ViewTasksState())
    val state = _state.asStateFlow()

    fun loadCategoryData(categoryId: Long) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val category = categoryService.getCategoryById(categoryId)
                val tasks = taskService.getTasksByCategoryId(categoryId)

                _state.update {
                    it.copy(
                        isLoading = false,
                        categoryTitle = category.title,

                        defaultCategoryType = category.defaultCategoryType,
                        tasks = tasks.groupBy { it.state }
                            .mapValues { entry ->
                                entry.value.map { task ->
                                    task.toUiState(category.defaultCategoryType)
                                }
                            },
                        tasksCount = tasks.groupingBy { it.state }.eachCount()
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Failed to load tasks"
                    )
                }
            }
        }
    }

    fun onTabSelected(state: State) {
        _state.update { it.copy(selectedTab = state) }
    }

    private fun Task.toUiState(categoryType: DefaultCategoryType?) =
        ViewTasksState.TaskUiState(
            id = id,
            title = title,
            description = description,
            priority = priority,
            state = state,
            createdAt = createdAt,
            defaultCategoryType = categoryType
        )
}