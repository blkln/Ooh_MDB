package com.example.domain

import com.example.domain.model.Movie
import com.example.domain.model.Result

interface MoviesRepository {

    suspend fun getMovies(name: String): Result<List<Movie>>
    suspend fun getMovieDetails(id: String): Result<Movie>

}