package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class TaskDetailsViewModel() : BaseViewModel<TaskDetailsUiState>(TaskDetailsUiState()) {

    fun hidden() {
        _state.update { it.copy(isVisible = false) }
    }
}
