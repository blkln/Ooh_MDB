package com.example.domain.interactors

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie
import com.example.domain.model.Result

class GetMovieDetails(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: String): Result<Movie> = moviesRepository.getMovieDetails(id)

}