package com.example.tinkofflabproject.data.entities

import com.google.gson.annotations.SerializedName


data class MoviePosters(
    @SerializedName("items")
    val cast: List<Poster>
)
