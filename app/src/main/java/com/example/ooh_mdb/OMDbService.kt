package com.example.ooh_mdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbService {

    @GET("?apikey=c07625ac")
    fun fetchMovies(@Query("s") name: String): Call<MovieResult>

    @GET("?apikey=c07625ac")
    fun getMovieDetails(@Query("i") imdbID: String): Call<Movie>

}