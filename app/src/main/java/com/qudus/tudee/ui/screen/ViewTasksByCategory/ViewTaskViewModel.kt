//package com.qudus.tudee.ui.screen.tasks_by_category
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.qudus.tudee.domain.entity.State
//import com.qudus.tudee.domain.service.CategoryService
//import com.qudus.tudee.domain.service.TaskService
//import kotlinx.coroutines.channels.Channel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.receiveAsFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch


package com.qudus.tudee.ui.screen.ViewTasksByCategory

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

 abstract class ViewTaskViewModel(
    private val taskService: TaskService,
    private val categoryService: CategoryService
) : BaseViewModel<ViewTaskScreenState>(ViewTaskScreenState()), ViewTasksInteraction {

    private val _events = Channel<ViewTaskEvents>()
    val events = _events.receiveAsFlow()

    override fun loadCategoryData(categoryId: Long) {
        _state.value = _state.value.copy(isLoading = true, error = null)

        tryToExecute(
            action = {
                val category = categoryService.getCategoryById(categoryId)
                val tasks = taskService.getTasksByCategoryId(categoryId)
                Pair(category, tasks)
            },
            onSuccess = { (category, tasks) ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    categoryId = categoryId,
                    categoryTitle = category.title,
                    isDefaultCategory = category.defaultCategoryType != null,
                    defaultCategoryType = category.defaultCategoryType,
                    //tasks = tasks.groupBy { it.state },
                    tasksCount = tasks.groupingBy { it.state }.eachCount()
                )
            },
            onError = { exception ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Failed to load tasks",
                    showSnackbar = true,
                    snackbarMessage = "Failed to load tasks"
                )
            }
        )
    }

    override fun selectTab(tab: State) {
        _state.value = _state.value.copy(selectedTab = tab)
    }

     override fun editCategory() {
         viewModelScope.launch {
             _state.value.defaultCategoryType?.let {
                 // إذا كانت فئة افتراضية
                 _events.send(ViewTaskEvents.NavigateBack)
             } ?: run {
                 // إذا كانت فئة مخصصة
                 _state.value.categoryId?.let { categoryId ->
                     _events.send(ViewTaskEvents.NavigateToEditCategory(categoryId))
                 } ?: _events.send(ViewTaskEvents.NavigateBack) // fallback
             }
         }
     }

    override fun dismissSnackbar() {
        _state.value = _state.value.copy(showSnackbar = false)
    }
}
    private fun Task.toUiState(): ViewTaskScreenState.TaskUiState {
        return ViewTaskScreenState.TaskUiState(
            id = id,
            title = title,
            description = description ?: "",
            priority = priority,
            state = state,
            createdAt = createdAt,
            defaultCategoryType = defaultCategoryType
        )
    }
