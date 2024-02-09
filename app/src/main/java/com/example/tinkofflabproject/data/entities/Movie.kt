package com.example.tinkofflabproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    @SerializedName("")
    val overview: String,
    @SerializedName("")
    val releaseDate: String,
    @SerializedName("")
    val poster: String,
    @SerializedName("")
    val backdrop: String,
    val revenue: Long,
    val budget: Long,
    val runtime: Long,
) : Serializable {
    companion object {
        val MOCK = Movie(
            100,
            "Samsung Bootcamp 2021",
            "Kotlin for Android",
            "2021-07-19",
            "",
            "",
            0,0,0
        )
    }
}