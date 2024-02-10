package com.example.tinkofflabproject.di

import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.data.repositories.MovieRepository
import com.example.tinkofflabproject.ui.movie.MovieViewModel
import com.example.tinkofflabproject.ui.popular.MoviePopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MovieRepository(get(), get()) }

    viewModel { MoviePopularViewModel(get()) }

    viewModel { (movie : Movie) -> MovieViewModel(get(), movie)}
}