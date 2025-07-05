package com.qudus.tudee.ui.state

import androidx.annotation.StringRes
import com.qudus.tudee.R


enum class StateUiState(@StringRes val status:Int){
    TODO(R.string.todo),
    IN_PROGRESS(R.string.in_progress),
    DONE(R.string.done)
}