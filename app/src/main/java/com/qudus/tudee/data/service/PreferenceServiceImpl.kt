package com.qudus.tudee.data.service

import com.qudus.tudee.data.repository.PreferenceService
import kotlinx.coroutines.flow.Flow

class PreferenceServiceImpl(
    private val preferenceService: PreferenceService
): com.qudus.tudee.domain.service.PreferenceService {
    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        preferenceService.setDarkTheme(isDarkTheme)
    }

    override fun getDarkTheme(): Flow<Boolean> {
       return preferenceService.getDarkTheme()
    }

    override suspend fun setIsCompleteOnBoarding(isCompleteOnBoarding: Boolean) {
        preferenceService.setIsCompleteOnBoarding(isCompleteOnBoarding)
    }

    override suspend fun getIsCompleteOnBoarding(): Boolean {
        return preferenceService.getIsCompleteOnBoarding()
    }

}