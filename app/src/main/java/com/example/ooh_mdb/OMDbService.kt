package com.example.ooh_mdb

import retrofit2.Call
import retrofit2.http.GET

interface OMDbService {

    @GET("?apikey=c07625ac&s=matrix")
    fun fetchMovies(): Call<MovieResult>

    @GET("?apikey=c07625ac&i=tt1201607") //sample search
    fun getMovieDetails(): Call<Movie>

}