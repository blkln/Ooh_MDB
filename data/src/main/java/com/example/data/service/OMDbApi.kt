package com.example.data.service


import com.example.data.model.ApiResponse
import com.example.data.model.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface OMDbApi {

    @GET(".")
    suspend fun getMovies(@Query("s") name: String): ApiResponse<List<MovieEntity>>

    @GET(".")
    suspend fun getMovieDetails(@Query("i") imdbID: String): MovieEntity

}