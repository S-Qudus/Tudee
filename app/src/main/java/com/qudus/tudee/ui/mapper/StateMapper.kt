package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.state.TaskStatusUiState

fun TaskStatusUiState.toState(): State {
    return when (this) {
        TaskStatusUiState.TODO -> State.TODO
        TaskStatusUiState.IN_PROGRESS -> State.IN_PROGRESS
        TaskStatusUiState.DONE -> State.DONE
    }
}

fun State.toTaskStatusUiState(): TaskStatusUiState {
    return when (this) {
        State.TODO -> TaskStatusUiState.TODO
        State.IN_PROGRESS -> TaskStatusUiState.IN_PROGRESS
        State.DONE -> TaskStatusUiState.DONE
    }
}
