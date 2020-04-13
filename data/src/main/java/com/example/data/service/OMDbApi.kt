package com.example.data.service


import com.example.data.model.MovieEntity
import com.example.data.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query


interface OMDbApi {

    @GET(".")
    suspend fun getMovies(@Query("s") name: String): MovieResult

    @GET(".")
    suspend fun getMovieDetails(@Query("i") imdbID: String): MovieEntity

}