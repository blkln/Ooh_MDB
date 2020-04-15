package com.example.ooh_mdb.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ErrorHandlerImpl
import com.example.data.MovieFetcher
import com.example.data.MoviesRepositoryImpl
import com.example.data.service.RetrofitProvider
import com.example.domain.model.Movie
import com.example.domain.interactors.GetMovies
import kotlinx.coroutines.launch
import com.example.domain.model.Result
import com.example.ooh_mdb.presentation.ErrorMessage

class SearchViewModel: ViewModel() {

    private val serviceProvider = RetrofitProvider().create()
    private val movieFetcher = MovieFetcher(serviceProvider)
    private val errorHandler = ErrorHandlerImpl()
    private val movieRepository = MoviesRepositoryImpl(movieFetcher, errorHandler)
    private val getMovies: GetMovies = GetMovies(movieRepository)


    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun loadMovies(name: String) {
        viewModelScope.launch {
            val result = getMovies(name)
            when (result) {
                is Result.Success -> {
                    _movies.postValue(result.data)
                }
                is Result.Error -> {
                    val message = ErrorMessage(result.error)
                    _errorMessage.postValue(message.generate())
                }
            }
        }
    }

}