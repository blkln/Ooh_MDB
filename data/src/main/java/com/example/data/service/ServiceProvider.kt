package com.example.data.service

import com.example.data.Constants

object ServiceProvider {

    val ombdService = RequestProvider.retrofit(Constants.OMDB_BASE_URL)
        .create(OMDbApi::class.java)

}