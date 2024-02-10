package com.example.tinkofflabproject.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tinkofflabproject.data.entities.Actor
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.data.repositories.MovieRepository
import com.example.tinkofflabproject.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieViewModel(
    private val repository: MovieRepository,
    private val movie: Movie
) : ViewModel() {
    val stateMovie : LiveData<State<Movie>> = liveData {
        emit(State.Loading())
        withContext(Dispatchers.IO){
            try {
                emit(State.Success(repository.getMovie(movie.id)))
            } catch (e : Exception){
                emit(State.Error<Movie>(e))
            }
        }
    }

    val stateActor : LiveData<State<List<Actor>>> = liveData {
        emit(State.Loading())
        withContext(Dispatchers.IO){
            try {
                emit(State.Success(repository.getMovieCredits(movie.id)))
            } catch (e : Exception){
                emit(State.Error<List<Actor>>(e))
            }
        }
    }
}