package com.example.ooh_mdb.presentation.di

import com.example.data.ErrorHandlerImpl
import com.example.data.MovieFetcher
import com.example.data.MoviesDataSource
import com.example.data.MoviesRepositoryImpl
import com.example.data.service.RetrofitProvider
import com.example.domain.MoviesRepository
import com.example.domain.interactors.GetMovieDetails
import com.example.domain.interactors.GetMovies
import com.example.ooh_mdb.presentation.details.DetailsViewModel
import com.example.ooh_mdb.presentation.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override = true) {
    viewModel { SearchViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

    //  Interactors
    factory { GetMovies(moviesRepository = get()) }
    factory { GetMovieDetails(moviesRepository = get()) }

    //  Interface types should be stated explicitly
    single<MoviesRepository> { MoviesRepositoryImpl(movieSource = get(), errorHandler = ErrorHandlerImpl()) }
    single<MoviesDataSource> { MovieFetcher(apiService = get()) }

    //  API service builder
    single { RetrofitProvider().create() }
}
