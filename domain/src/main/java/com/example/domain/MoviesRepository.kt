package com.example.domain

import com.example.domain.model.Movie

interface MoviesRepository {

    suspend fun getMovies(name: String): List<Movie>
    suspend fun getMovieDetails(id: String): Movie

}