package com.example.data

import com.example.data.model.mapToDomain
import com.example.data.service.ServiceProvider
import com.example.domain.model.Movie

class MovieFetcher: MoviesDataSource {

    private val service = ServiceProvider.ombdService

    override suspend fun getMovies(name: String): List<Movie> = service.getMovies(name).Search.mapToDomain()

    override suspend fun getMovieDetails(id: String): Movie = service.getMovieDetails(id).mapToDomain()
}