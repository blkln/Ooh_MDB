package com.example.domain.interactors

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie

class GetMovies(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(name: String): List<Movie> = moviesRepository.getMovies(name)

}