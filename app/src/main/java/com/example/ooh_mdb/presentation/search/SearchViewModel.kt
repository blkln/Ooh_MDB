package com.example.ooh_mdb.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MoviesRepository
import com.example.domain.Movie
import com.example.interactors.GetMovies
import com.example.data.MovieFetcher
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val movieFetcher = MovieFetcher()
    private val movieRepository = MoviesRepository(movieFetcher)
    private val getMovies: GetMovies = GetMovies(movieRepository)


    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun loadMovies(name: String) {
        viewModelScope.launch {
            _movies.postValue(getMovies(name))
        }
    }

}