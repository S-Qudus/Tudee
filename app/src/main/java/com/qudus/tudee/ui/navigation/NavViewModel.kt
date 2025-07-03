package com.qudus.tudee.ui.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavViewModel(
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination = _startDestination

    init {
        viewModelScope.launch {
            val isComplete = preferenceService.getIsCompleteOnBoarding()
            _startDestination.value =
                if (isComplete) Screen.HomeScreen.route else Screen.OnBoardingScreen.route
        }
    }
}