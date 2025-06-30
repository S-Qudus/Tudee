package com.qudus.tudee.ui.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.ui.screen.tasksScreen.state.TaskUiState
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.StateUiState
import java.time.format.DateTimeFormatter
import com.qudus.tudee.domain.entity.State

@RequiresApi(Build.VERSION_CODES.O)
object TasksScreenMapper {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    fun map(task: Task): TaskUiState = TaskUiState(
        id = task.id,
        title = task.title,
        description = task.description,
        createdAt = task.createdAt.format(dateFormatter),
        priority = map(task.priority),
        state = map(task.state),
        categoryId = task.categoryId,
    )

    private fun map(priority: Priority): PriorityUiState = when (priority) {
        Priority.HIGH -> PriorityUiState.HIGH
        Priority.MEDIUM -> PriorityUiState.MEDIUM
        Priority.LOW -> PriorityUiState.LOW
    }

    private fun map(state: State): StateUiState = when (state) {
        State.TODO -> StateUiState.TODO
        State.IN_PROGRESS -> StateUiState.IN_PROGRESS
        State.DONE -> StateUiState.DONE
    }

    fun mapToDomain(stateUi: StateUiState): State = when (stateUi) {
        StateUiState.TODO -> State.TODO
        StateUiState.IN_PROGRESS -> State.IN_PROGRESS
        StateUiState.DONE -> State.DONE
    }
}