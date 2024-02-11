package com.example.tinkofflabproject.data.entities

import com.google.gson.annotations.SerializedName


data class MoviePosters(
    @SerializedName("total")
    val total : Int,
    @SerializedName("totalPages")
    val totalPages : Int,
    @SerializedName("items")
    val items: List<Poster>
)
