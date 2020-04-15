package com.example.data

import com.example.domain.model.Movie
import com.example.domain.MoviesRepository
import com.example.domain.model.ErrorHandler
import com.example.domain.model.Result

class MoviesRepositoryImpl(private val movieSource: MoviesDataSource, private val errorHandler: ErrorHandler): MoviesRepository {

    override suspend fun getMovies(name: String) : Result<List<Movie>> {
        return try {
            Result.Success(movieSource.getMovies(name))
        } catch (e: Exception) {
            Result.Error(errorHandler.getError(e))
        }
    }

    override suspend fun getMovieDetails(id: String): Result<Movie> {
        return try {
            Result.Success(movieSource.getMovieDetails(id))
        } catch (e: Exception) {
            Result.Error(errorHandler.getError(e))
        }
    }

}

interface MoviesDataSource {
    suspend fun getMovies(name: String): List<Movie>
    suspend fun getMovieDetails(id: String): Movie
}