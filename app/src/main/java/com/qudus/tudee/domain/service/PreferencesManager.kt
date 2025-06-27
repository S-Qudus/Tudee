package com.qudus.tudee.domain.service

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    val isDarkTheme: Flow<Boolean>
    suspend fun setDarkTheme(enabled: Boolean)
} 