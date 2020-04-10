package com.example.interactors

import com.example.data.MoviesRepository
import com.example.domain.Movie

class GetMovies(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(name: String): List<Movie> = moviesRepository.getMovies(name)

}