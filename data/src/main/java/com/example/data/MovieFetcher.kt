package com.example.data

import com.example.data.model.mapToDomain
import com.example.data.service.OMDbApi
import com.example.domain.model.Movie
import kotlin.Exception

class MovieFetcher(private val apiService: OMDbApi): MoviesDataSource {

    override suspend fun getMovies(name: String): List<Movie> {
        val response = apiService.getMovies(name)
        if (response.error != null) {
            throw Exception(response.error)
        } else {
            return response.search.mapToDomain()
        }
    }

    override suspend fun getMovieDetails(id: String): Movie {
        val response = apiService.getMovieDetails(id)
        if (response.error != null) {
            throw Exception(response.error)
        } else {
            return response.mapToDomain()
        }
    }

}