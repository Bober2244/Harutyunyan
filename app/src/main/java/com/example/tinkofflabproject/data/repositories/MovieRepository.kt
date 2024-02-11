package com.example.tinkofflabproject.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tinkofflabproject.data.dao.MovieDao
import com.example.tinkofflabproject.data.entities.Genre
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.net.MovieApi

class MovieRepository(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) {

    val moviePopularFlow
        get() = Pager(
            pagingSourceFactory = { MovieDataSource(movieApi) },
            config = PagingConfig(
                pageSize = 20
            )
        ).flow

    suspend fun getMovie(id : Long) : Movie {
        var movie = movieDao.getMoveById(id)
        if (movie == null){
            movie = movieApi.getMovie(id)
            movieDao.addMovie(movie)
        }
        return movie
    }

    suspend fun getMovieGenres(movieId: Long) : List<Genre> {
        val genres = movieApi.getMovieGenres(movieId)
        return genres.genres
    }

    //TODO: Раскомитить
    /*
    suspend fun getMovieCredits(movieId : Long) : List<Actor> {
        val credits = movieApi.getMovieCredits(movieId)
        return credits.cast
    }
     */
}