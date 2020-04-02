package com.example.ooh_mdb

//TODO: didn't compile until 'item' changed to 'search'
data class MovieResult(val Search: List<Movie>)

data class Movie(
    val Title: String?,
    val Year: String?,
    val imdbID: String?,
    val Type: String?,
    val Poster: String?)


/*
"Title": "Harry Potter and the Deathly Hallows: Part 2",
"Year": "2011",
"imdbID": "tt1201607",
"Type": "movie",
"Poster": "https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
*/
