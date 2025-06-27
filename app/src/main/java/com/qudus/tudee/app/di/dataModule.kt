package com.qudus.tudee.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.qudus.tudee.data.dataSource.PreferenceServiceImpl
import com.qudus.tudee.data.database.TudeeDatabase
import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.repository.PreferenceService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tudee_app_data_store")

val dataModule = module {

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single<PreferenceService> { PreferenceServiceImpl(get()) }

    single<TudeeDatabase> {
        Room.databaseBuilder(
            androidContext(),
            TudeeDatabase::class.java,
            "tudee_database"
        ).build()
    }

    single<TaskDao> {
        get<TudeeDatabase>().taskDao()
    }
}

