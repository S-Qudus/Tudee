package com.qudus.tudee.ui.screen.configration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConfigurationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val isComplete = preferenceService.getIsCompleteOnBoarding()
            preferenceService.getDarkTheme().collectLatest { isDark ->
                _uiState.update {
                    it.copy(
                        isCompleteOnBoarding = isComplete,
                        isDarkTheme = isDark,
                        startDestination = if (isComplete) Screen.HomeScreen.route else Screen.OnBoardingScreen.route
                    )
                }

            }
        }
    }
}