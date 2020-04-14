package com.example.data

import com.example.data.model.mapToDomain
import com.example.data.service.OMDbApi
import com.example.domain.model.Movie

class MovieFetcher(private val apiService: OMDbApi): MoviesDataSource {
    override suspend fun getMovies(name: String): List<Movie> = apiService.getMovies(name).Search.mapToDomain()
    override suspend fun getMovieDetails(id: String): Movie = apiService.getMovieDetails(id).mapToDomain()
}