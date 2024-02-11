package com.example.tinkofflabproject.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tinkofflabproject.data.repositories.MovieRepository

class MoviePopularViewModel(private val repository: MovieRepository) : ViewModel() {
    val moviePage = repository.moviePopularFlow
        .cachedIn(viewModelScope).asLiveData()
}
