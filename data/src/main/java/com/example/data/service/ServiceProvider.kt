package com.example.data.service

object ServiceProvider {

    const val OMDB_BASE_URL = "http://www.omdbapi.com/"

    val ombdService = RequestProvider.retrofit(OMDB_BASE_URL)
        .create(OMDbApi::class.java)

}