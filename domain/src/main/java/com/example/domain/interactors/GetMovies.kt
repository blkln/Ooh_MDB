package com.example.domain.interactors

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie
import com.example.domain.model.Result

class GetMovies(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(name: String): Result<List<Movie>> = moviesRepository.getMovies(name)

}