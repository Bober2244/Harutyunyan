package com.example.tinkofflabproject.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkofflabproject.data.dao.MovieDao
import com.example.tinkofflabproject.data.entities.Movie

@Database(
    entities = [Movie::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
}
