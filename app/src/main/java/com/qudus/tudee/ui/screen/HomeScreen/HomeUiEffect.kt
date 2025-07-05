package com.qudus.tudee.ui.screen.HomeScreen

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed interface HomeUiEffect {
    class NavigateBackWithSuccessState(val isSuccess: Boolean): HomeUiEffect
    class NavigateBackWithCancelation(): HomeUiEffect
}

object UiEventBus {
    private val _effect = MutableSharedFlow<HomeUiEffect>()
    val effect = _effect.asSharedFlow()

    suspend fun emitEffect(effect: HomeUiEffect) {
        _effect.emit(effect)
    }
}
