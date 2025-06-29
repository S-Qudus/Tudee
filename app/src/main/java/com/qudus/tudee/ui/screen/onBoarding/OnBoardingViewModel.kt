package com.qudus.tudee.ui.screen.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.R
import com.qudus.tudee.domain.service.PreferenceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val preferenceService: PreferenceService
) : ViewModel(), OnBoardingInteractionListener {

    private val _uiState = MutableStateFlow(OnBoardingUiState())
    val uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    init {
        loadOnBoardingData()
    }

    private fun loadOnBoardingData() {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceService.getDarkTheme().collect { isDarkTheme ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isCompleted = false,
                        isDarkTheme = isDarkTheme,
                        onBoardingItemUiState = getOnBoardingItem(),
                    )
                }
            }

        }


    }

    override fun updatePage(index: Int) {
        _uiState.update {
            it.copy(currentPage = index)
        }
    }

    override fun onClickSkipText() {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceService.setIsCompleteOnBoarding(true)
            _uiState.update { currentState ->
                currentState.copy(isCompleted = true)
            }
        }
    }

    override fun onClickNextButton() {
        if (uiState.value.currentPage == uiState.value.onBoardingItemUiState.lastIndex) {
            onClickSkipText()
        } else {
            _uiState.update {
                it.copy(currentPage = it.currentPage + 1)
            }
        }
    }

    private fun getOnBoardingItem(): List<OnBoardingItemUiState> {
        return listOf(
            OnBoardingItemUiState(
                title = R.string.overwhelmed_with_tasks,
                description = R.string.let_s_bring_some_order_to_the_chaos_tudee_is_here_to_help_you_sort_plan_and_breathe_easier,
                imageRes = R.drawable.image_happy_tudee_onboarding
            ),
            OnBoardingItemUiState(
                title = R.string.uh_oh_procrastinating_again,
                description = R.string.tudee_not_mad_just_a_little_disappointed_let_s_get_back_on_track_together,
                imageRes = R.drawable.image_tudee_onboarding_two
            ),
            OnBoardingItemUiState(
                title = R.string.let_s_complete_task_and_celebrate_together,
                description = R.string.progress_is_progress_tudee_will_celebrate_you_on_for_every_win_big_or_small,
                imageRes = R.drawable.image_tudee_onbaring_three
            ),
        )
    }
}