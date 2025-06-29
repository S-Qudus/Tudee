package com.qudus.tudee.ui.screen.editTask

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.mapper.toTaskUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import kotlinx.coroutines.flow.update

class EditTaskViewModel(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) : TaskEditorViewModel(), EditTaskInteraction {

    //todo: receive the real task id that i need to edit
    val id = 2L

    init {
        tryToExecute(
            action = { taskService.getTaskById(id) },
            onSuccess = ::onGetCurrentTaskSuccess,
            onError = ::onGetCurrentTaskError,
        )
    }

    private fun onGetCurrentTaskSuccess(oldTask: Task) {
        getExistingCategories(oldTask.categoryId)
        updateUiStateWithOldState(oldTask.toTaskUiState())
    }

    private fun getExistingCategories(taskCategoryId: Long) {
        collectFlow(
            flow = categoryService.getCategories(),
            onEach = { categories ->
                _state.update {
                    it.copy(
                        categoryUiStates = categories.map {
                            it.toCategoryItemUiState(categoryId = taskCategoryId)
                        }
                    ) }
            },
            onError = ::onGetCategoriesError,
        )
    }

    private fun updateUiStateWithOldState(oldTaskUiState: TaskEditorUiState) {
        _state.update {
            it.copy(
                title = oldTaskUiState.title,
                description = oldTaskUiState.description,
                date = oldTaskUiState.date,
                priorityUiStates = oldTaskUiState.priorityUiStates,
                categoryUiStates = oldTaskUiState.categoryUiStates,
            )
        }
    }

    private fun onGetCurrentTaskError(exception: TudeeExecption) {

    }

    override fun onEditTaskClicked() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            action = { taskService.updateTask(state.value.toTask(taskId = id)) },
            onSuccess = ::onEditTaskSuccess,
            onError = ::onSubmitTaskError,
        )
    }

    private fun onEditTaskSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSheetOpen = false) }

        //todo: move to the prev screen with success params true
    }
}