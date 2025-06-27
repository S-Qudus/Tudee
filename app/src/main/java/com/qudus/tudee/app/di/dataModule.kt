package com.qudus.tudee.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.qudus.tudee.data.dataSource.PreferenceServiceImpl
import com.qudus.tudee.data.repository.PreferenceService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<Context> {
        androidApplication()
    }
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {
                get<Context>().preferencesDataStoreFile("tudee_preferences")
            }
        )
    }
    single<PreferenceService> { PreferenceServiceImpl(get()) }
}