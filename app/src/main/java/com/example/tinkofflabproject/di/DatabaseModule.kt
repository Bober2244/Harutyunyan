package com.example.tinkofflabproject.di

import androidx.room.Room
import com.example.tinkofflabproject.data.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            "database"
        ).build()
    }

    single { get<MovieDatabase>().movieDao }
}
