package com.example.ooh_mdb.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MoviesRepository
import com.example.domain.Movie
import com.example.interactors.GetMovieDetails
import com.example.ooh_mdb.framework.MovieFetcher
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val movieFetcher = MovieFetcher()
    private val movieRepository = MoviesRepository(movieFetcher)
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