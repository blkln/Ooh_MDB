package com.example.data

import com.example.domain.Movie

class MoviesRepository(private val movieSource: MoviesDataSource) {

    suspend fun getMovies(name: String) : List<Movie> {
        return movieSource.getMovies(name)
    }

    suspend fun getMovieDetails(id: String): Movie {
        return movieSource.getMovieDetails(id)
    }

}

interface MoviesDataSource {

    suspend fun getMovies(name: String): List<Movie>
    suspend fun getMovieDetails(id: String): Movie
}