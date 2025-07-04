package com.qudus.tudee.ui.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavViewModel(
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination = _startDestination

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        loadOnBoardingStatus()
        loadThemePreference()
    }

    private fun loadOnBoardingStatus() {
        viewModelScope.launch {
            val isComplete = preferenceService.getIsCompleteOnBoarding()
            _startDestination.value =
                if (isComplete) Screen.HomeScreen.route else Screen.OnBoardingScreen.route
        }
    }

    private fun loadThemePreference() {
        viewModelScope.launch {
            preferenceService.getDarkTheme().collect { isDarkTheme ->
                _isDarkTheme.value = isDarkTheme
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            preferenceService.setDarkTheme(newTheme)
            _isDarkTheme.value = newTheme
        }
    }
}