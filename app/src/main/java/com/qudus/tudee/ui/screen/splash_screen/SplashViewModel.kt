package com.qudus.tudee.ui.screen.splash_screen

import com.qudus.tudee.data.repository.PreferenceService
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val preferenceService: PreferenceService,
) : BaseViewModel<SplashUiState>(SplashUiState()){

    init {
        getIsCompleteOnBoarding()
    }

    fun getIsCompleteOnBoarding(){
        tryToExecute(
            action = preferenceService::getIsCompleteOnBoarding,
            onSuccess = ::onGetIsCompleteOnBoardingSuccess,
            onError = ::onGetIsCompleteOnBoardingError
        )
    }

    fun onGetIsCompleteOnBoardingSuccess(isComplete: Boolean){
        _state.update { it.copy(isCompleteOnBoarding = isComplete) }
    }

    fun onGetIsCompleteOnBoardingError(exception: TudeeExecption){
        // go to onbording or home
    }

//    fun getIsDarkTheme(){
//        collectFlow(
//            flow = preferenceService.getDarkTheme(),
//            onEach = ,
//            onError = ,
//        )
//    }




}