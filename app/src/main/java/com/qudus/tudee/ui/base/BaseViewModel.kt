package com.qudus.tudee.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.exception.TudeeExecption
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<STATE>(initialState: STATE): ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val state = _state.asStateFlow()

    protected fun <T> tryToExecute(
        action: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (TudeeExecption) -> Unit,
        completion: () -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                onSuccess(action())
            } catch (exception: TudeeExecption) {
                onError(exception)
            } finally {
                completion()
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        onEach: (T) -> Unit,
        onError: (TudeeExecption) -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                flow.collect { value -> onEach(value) }
            } catch (e: TudeeExecption) {
                onError(e)
            }
        }
    }
} 