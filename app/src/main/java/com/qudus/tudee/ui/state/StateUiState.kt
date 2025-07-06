package com.qudus.tudee.ui.state

import androidx.annotation.StringRes
import com.qudus.tudee.R


enum class StateUiState(@StringRes val status:Int){
    IN_PROGRESS(R.string.in_progress),
    TODO(R.string.todo),
    DONE(R.string.done)
}