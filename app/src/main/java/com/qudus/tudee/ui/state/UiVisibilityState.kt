package com.qudus.tudee.ui.state

data class UiVisibilityState(
    val isLoading: Boolean = false,
    val showAddTaskSheet: Boolean = false,
    val showEditTaskSheet: Boolean = false,
    val showTaskDetailsBottomSheet: Boolean = false
) 