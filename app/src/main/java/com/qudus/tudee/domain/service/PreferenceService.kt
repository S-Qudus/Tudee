package com.qudus.tudee.domain.service

import kotlinx.coroutines.flow.Flow

interface PreferenceService {
    suspend fun setDarkTheme(isDarkTheme: Boolean)
    fun getDarkTheme(): Flow<Boolean>
    suspend fun setIsCompleteOnBoarding(isCompleteOnBoarding: Boolean)
    suspend fun getIsCompleteOnBoarding(): Boolean
}