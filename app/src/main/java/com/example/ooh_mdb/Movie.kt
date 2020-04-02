package com.example.ooh_mdb

//TODO: didn't compile until 'item' changed to 'search'
data class MovieResult(val Search: List<Movie>)

data class Movie(
    val Title: String?,
    val Year: String?,
    val imdbID: String?,
    val Type: String?,
    val Poster: String?,
    val Runtime: String?,
    val Genre: String?,
    val Director: String?,
    val Writer: String?,
    val Actors: String?,
    val Plot: String?)

