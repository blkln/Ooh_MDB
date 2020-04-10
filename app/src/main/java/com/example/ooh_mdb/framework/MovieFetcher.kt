package com.example.ooh_mdb.framework

import com.example.data.MovieSource
import com.example.domain.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieFetcher: MovieSource {
    private val service: OMDbService

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(OMDbService::class.java)
    }

    override suspend fun getMovies(name: String): List<Movie> = service.fetchMovies(name).Search//{
//        val result = service.fetchMovies(name)
//        return result.Search
//    }

    override suspend fun getMovieDetails(id: String): Movie = service.getMovieDetails(id)

}