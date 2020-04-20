package com.example.ooh_mdb.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ErrorHandlerImpl
import com.example.data.MovieFetcher
import com.example.data.MoviesRepositoryImpl
import com.example.data.service.RetrofitProvider
import com.example.domain.interactors.GetMovieDetails
import com.example.domain.model.Movie
import com.example.domain.model.Result
import com.example.ooh_mdb.presentation.ErrorMessage
import kotlinx.coroutines.launch

class DetailsViewModel(private val getMovieDetails: GetMovieDetails): ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
    get() = _movie

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun loadMovieDetails(imdbID: String) {
        viewModelScope.launch {
            val result = getMovieDetails(imdbID)
            when (result) {
                is Result.Success -> {
                    _movie.postValue(result.data)
                }
                is Result.Error -> {
                    val message = ErrorMessage(result.error)
                    _errorMessage.postValue(message.generate())
                }
            }
        }
    }

}