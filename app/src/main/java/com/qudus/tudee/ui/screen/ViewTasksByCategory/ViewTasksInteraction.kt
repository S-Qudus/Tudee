package com.qudus.tudee.ui.screen.ViewTasksByCategory


import com.qudus.tudee.domain.entity.State

interface ViewTasksInteraction {
    fun loadCategoryData(categoryId: Long)
    fun selectTab(tab: State)
    fun editCategory()
    fun onTaskClicked(taskId: Long)
    fun dismissSnackbar()
}