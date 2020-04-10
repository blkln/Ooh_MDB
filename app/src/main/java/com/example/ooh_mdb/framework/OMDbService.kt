package com.example.ooh_mdb.framework

import com.example.domain.Movie
import com.example.domain.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbService {

    @GET("?apikey=c07625ac")
    suspend fun fetchMovies(@Query("s") name: String): MovieResult

    @GET("?apikey=c07625ac")
    suspend fun getMovieDetails(@Query("i") imdbID: String): Movie

}