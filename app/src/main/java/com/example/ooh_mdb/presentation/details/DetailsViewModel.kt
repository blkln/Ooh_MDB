package com.example.ooh_mdb.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MoviesRepositoryImpl
import com.example.domain.model.Movie
import com.example.domain.interactors.GetMovieDetails
import com.example.data.MovieFetcher
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val movieFetcher = MovieFetcher()
    private val movieRepository = MoviesRepositoryImpl(movieFetcher)
    private val getMovieDetails: GetMovieDetails = GetMovieDetails(movieRepository)

    private val _movie = MutableLiveData<Movie>(
        Movie(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    )
    val movie: LiveData<Movie>
    get() = _movie

    fun loadMovieDetails(imdbID: String) {
        viewModelScope.launch {
            _movie.postValue(getMovieDetails(imdbID))
        }
    }

}