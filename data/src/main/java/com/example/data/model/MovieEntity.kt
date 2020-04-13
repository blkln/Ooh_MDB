package com.example.data.model

import com.example.domain.model.Movie
import com.squareup.moshi.Json

data class MovieResult(val Search: List<MovieEntity>)

data class MovieEntity (
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Year")
    val year: String,
    val imdbID: String,
    @field:Json(name = "Type")
    val type: String,
    @field:Json(name = "Poster")
    val poster: String,
    @field:Json(name = "Runtime")
    val runtime: String,
    @field:Json(name = "Genre")
    val genre: String,
    @field:Json(name = "Director")
    val director: String,
    @field:Json(name = "Writer")
    val writer: String,
    @field:Json(name = "Actors")
    val actors: String,
    @field:Json(name = "Plot")
    val plot: String
)

fun MovieEntity.mapToDomain(): Movie =
    Movie(
        title,
        year,
        imdbID,
        type,
        poster,
        runtime,
        genre,
        director,
        writer,
        actors,
        plot
    )

fun List<MovieEntity>.mapToDomain(): List<Movie> = map { it.mapToDomain() }


