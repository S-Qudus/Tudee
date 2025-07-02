package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.state.PriorityUiState

fun PriorityUiState.toPriority(): Priority {
    return when (this) {
        PriorityUiState.HIGH -> Priority.HIGH
        PriorityUiState.MEDIUM -> Priority.MEDIUM
        PriorityUiState.LOW -> Priority.LOW
    }
}

fun Priority.toPriorityUiState(): PriorityUiState {
    return when (this) {
        Priority.HIGH -> PriorityUiState.HIGH
        Priority.MEDIUM -> PriorityUiState.MEDIUM
        Priority.LOW -> PriorityUiState.LOW
    }
}