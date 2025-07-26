package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.state.TaskStateUiState

fun TaskStateUiState.toState(): State {
    return when (this) {
        TaskStateUiState.TODO -> State.TODO
        TaskStateUiState.IN_PROGRESS -> State.IN_PROGRESS
        TaskStateUiState.DONE -> State.DONE
    }
}

fun State.toTaskStatusUiState(): TaskStateUiState {
    return when (this) {
        State.TODO -> TaskStateUiState.TODO
        State.IN_PROGRESS -> TaskStateUiState.IN_PROGRESS
        State.DONE -> TaskStateUiState.DONE
    }
}
