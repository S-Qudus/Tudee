package com.qudus.tudee.data.repository

import kotlinx.coroutines.flow.Flow

interface ConfigurationDataSource {
    suspend fun setDarkTheme(isDarkTheme: Boolean)
    fun getDarkTheme(): Flow<Boolean>
    suspend fun setIsCompleteOnBoarding(isCompleteOnBoarding: Boolean)
    suspend fun getIsCompleteOnBoarding(): Boolean
}