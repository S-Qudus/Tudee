package com.qudus.tudee.app.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.qudus.tudee.data.database.RoomCallBackImpl
import com.qudus.tudee.data.database.TudeeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single(named(DatabaseConstants.StringQualifiers.DATABASE_NAME)) { DatabaseConstants.ParametersValue.DATABASE_NAME }


    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = TudeeDatabase::class.java,
            name = get<String>(named(DatabaseConstants.StringQualifiers.DATABASE_NAME))
        )
            .addCallback(callback = get<RoomDatabase.Callback>())
            .build()
    }

    single<RoomDatabase.Callback> {
        RoomCallBackImpl { get<TudeeDatabase>() }
    }

    single { get<TudeeDatabase>().taskDao() }
    single { get<TudeeDatabase>().categoryDao() }
}

object DatabaseConstants {
    object StringQualifiers {
        const val DATABASE_NAME = "DB_NAME"
    }

    object ParametersValue {
        const val DATABASE_NAME = "tudee_database"
    }
}