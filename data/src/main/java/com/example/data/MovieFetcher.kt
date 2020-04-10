package com.example.data

import com.example.data.service.ServiceProvider
import com.example.domain.Movie
import com.example.data.MoviesDataSource as MoviesDataSource

class MovieFetcher: MoviesDataSource {

    private val service = ServiceProvider.ombdService

    override suspend fun getMovies(name: String): List<Movie> = service.getMovies(name).Search

    override suspend fun getMovieDetails(id: String): Movie = service.getMovieDetails(id)
}