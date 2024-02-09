package com.example.tinkofflabproject.data.entities

import com.google.gson.annotations.SerializedName

data class MoviesPage(
    val page: Int,
    @SerializedName("")
    val movies : List<Movie>,
    @SerializedName("")
    val totalPages : Int,
    @SerializedName("")
    val totalResults : Int,
)
