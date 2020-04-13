package com.example.domain.interactors

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie

class GetMovieDetails(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: String): Movie = moviesRepository.getMovieDetails(id)

}