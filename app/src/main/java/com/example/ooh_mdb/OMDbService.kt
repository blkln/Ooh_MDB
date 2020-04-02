package com.example.ooh_mdb

import retrofit2.Call
import retrofit2.http.GET
import com.example.ooh_mdb.MovieResult


interface OMDbService {

    @GET("?apikey=c07625ac&s=matrix")
    fun fetchMovies(): Call<MovieResult>

}