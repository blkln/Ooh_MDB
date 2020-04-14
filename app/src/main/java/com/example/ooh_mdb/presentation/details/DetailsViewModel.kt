package com.example.ooh_mdb.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MovieFetcher
import com.example.data.MoviesRepositoryImpl
import com.example.data.service.RetrofitProvider
import com.example.domain.interactors.GetMovieDetails
import com.example.domain.model.Movie
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val serviceProvider = RetrofitProvider().create()
    private val movieFetcher = MovieFetcher(serviceProvider)
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