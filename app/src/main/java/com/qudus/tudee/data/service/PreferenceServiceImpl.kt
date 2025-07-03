package com.qudus.tudee.data.service

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.qudus.tudee.domain.service.PreferenceService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferenceServiceImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferenceService {
    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_THEME] = isDarkTheme
        }
    }

    override fun getDarkTheme(): Flow<Boolean> {
        return dataStore.data.map {
            it[IS_DARK_THEME] == true
        }
    }

    override suspend fun setIsCompleteOnBoarding(isCompleteOnBoarding: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_COMPLETE_ON_BOARDING] = isCompleteOnBoarding
        }
    }

    override suspend fun getIsCompleteOnBoarding(): Boolean {
        return dataStore.data
            .map { it[IS_COMPLETE_ON_BOARDING] == true }
            .first()
    }

    companion object {
        private val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
        private val IS_COMPLETE_ON_BOARDING = booleanPreferencesKey("IS_COMPLETE_ON_BOARDING")
    }

}