package com.example.ooh_mdb

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieFetcher {
    private val service: OMDbService

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) //1
            .addConverterFactory(GsonConverterFactory.create()) //2
            .build()
        service = retrofit.create(OMDbService::class.java) //3
    }

    fun getMovies(callback: Callback<MovieResult>) { //4
        service.fetchMovies().enqueue(callback)
    }

    fun getMovieDetails(imdbID: String, callback: Callback<Movie>) {
        service.getMovieDetails(imdbID).enqueue(callback)
    }

}