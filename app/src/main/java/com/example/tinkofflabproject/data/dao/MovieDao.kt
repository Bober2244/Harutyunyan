package com.example.tinkofflabproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tinkofflabproject.data.entities.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    fun getMoveById(movieId : Long) : Movie?
}