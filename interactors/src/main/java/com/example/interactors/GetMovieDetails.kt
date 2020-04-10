package com.example.interactors

import com.example.data.MoviesRepository
import com.example.domain.Movie

class GetMovieDetails(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: String): Movie = moviesRepository.getMovieDetails(id)

}