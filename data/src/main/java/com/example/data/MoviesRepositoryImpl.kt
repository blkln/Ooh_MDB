package com.example.data

import com.example.domain.model.Movie
import com.example.domain.MoviesRepository

class MoviesRepositoryImpl(private val movieSource: MoviesDataSource): MoviesRepository {

    override suspend fun getMovies(name: String) : List<Movie> {
        return movieSource.getMovies(name)
    }

    override suspend fun getMovieDetails(id: String): Movie {
        return movieSource.getMovieDetails(id)
    }

}

interface MoviesDataSource {

    suspend fun getMovies(name: String): List<Movie>
    suspend fun getMovieDetails(id: String): Movie
}