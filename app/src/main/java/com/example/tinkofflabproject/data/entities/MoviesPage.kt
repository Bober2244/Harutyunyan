package com.example.tinkofflabproject.data.entities

import com.google.gson.annotations.SerializedName

data class MoviesPage(
    @SerializedName("total")
    val totalResults : Int,
    @SerializedName("totalPages")
    val totalPages : Int,
    @SerializedName("items")
    val movies : List<Movie>,
)
