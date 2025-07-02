package com.qudus.tudee.ui.screen.viewTask

import com.qudus.tudee.ui.state.StateUiState

interface ViewTasksInteraction {
    fun onBackClicked()
    fun onEditCategoryClicked()
    fun onTaskClicked(taskId: Long)
    fun onTabSelected(state: StateUiState)
}