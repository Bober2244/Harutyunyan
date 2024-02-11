package com.example.tinkofflabproject.net

import com.example.tinkofflabproject.data.entities.Genres
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.data.entities.MoviePosters
import com.example.tinkofflabproject.data.entities.MoviesPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/api/v2.2/films/collections")
    suspend fun getPopularMoviesPage(
        @Query("type") type : String,
        @Query("page") pageNum : Int
    ) : MoviesPage

    @GET("/api/v2.2/films/{id}")
    suspend fun getMovie(
        @Path("id") id : Long
    ) : Movie

    @GET("/api/v2.2/films/{id}")
    suspend fun getMovieGenres(
        @Path("id") id : Long
    ) : Genres

    @GET("/api/v2.2/films/{id}/images")
    suspend fun getMovieCredits(
        @Query("id") id : Long,
        @Query("type") type: String
    ) : MoviePosters
}