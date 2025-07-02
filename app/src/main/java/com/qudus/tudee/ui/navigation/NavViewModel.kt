package com.qudus.tudee.ui.navigation

import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class NavViewModel(
    private val preferenceService: PreferenceService
): BaseViewModel<String>(Screen.HomeScreen.route) {

    init {
        tryToExecute(
            action = preferenceService::getIsCompleteOnBoarding,
            onSuccess = ::onGetIsCompleteOnBoardingSuccess,
            onError = { _state.update { Screen.OnBoardingScreen.route } },
        )
    }

    fun onGetIsCompleteOnBoardingSuccess(isComplete: Boolean){
        if (isComplete) _state.update { Screen.HomeScreen.route } else _state.update { Screen.OnBoardingScreen.route }
    }

}