package com.example.tinkofflabproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("kinopoiskId")
    val id: Long,
    @SerializedName("nameRu")
    val title: String,
    @SerializedName("year")
    val releaseDate: String,
    @SerializedName("posterUrl")
    val poster: String,
    @SerializedName("posterUrlPreview")
    val backdrop: String,
    @ColumnInfo(name = "description", defaultValue = "")
    @SerializedName("description")
    val description : String,
    /*@ColumnInfo(name = "runtime", defaultValue = "0")
    @SerializedName("filmLength")
    val runtime : Long,*/
) : Serializable
