package com.example.tinkofflabproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("kinopoiskId")
    val id: Long,
    @SerializedName("nameOriginal")
    val title: String,
    @SerializedName("year")
    val releaseDate: String,
    @SerializedName("posterUrl")
    val poster: String,
    @SerializedName("posterUrlPreview")
    val backdrop: String,
) : Serializable {
    companion object {
        val MOCK = Movie(
            100,
            "WoW",
            "It's for me?",
            "2024-02-10",
            "",
        )
    }
}