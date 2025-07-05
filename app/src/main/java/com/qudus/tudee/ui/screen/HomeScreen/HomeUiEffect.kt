package com.qudus.tudee.ui.screen.HomeScreen

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed interface HomeUiEffect {
    class NavigateBackFromAddTaskWithSuccessState(val isSuccess: Boolean): HomeUiEffect
    class NavigateBackWithCancelation(): HomeUiEffect

    class NavigateToTaskDetails(val taskId: Long): HomeUiEffect
    class NavigateBakeFromTaskDetail(): HomeUiEffect
    class NavigateToEditTask(val taskId: Long): HomeUiEffect
}

object UiEventBus {
    private val _effect = MutableSharedFlow<HomeUiEffect>(replay = 1)
    val effect = _effect.asSharedFlow()

    suspend fun emitEffect(effect: HomeUiEffect) {
        _effect.emit(effect)
    }
}
