package com.example.tinkofflabproject.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkofflabproject.data.dao.MovieDao
import com.example.tinkofflabproject.data.entities.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
}