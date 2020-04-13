package com.example.data.service

import com.example.data.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RequestProvider {

    private val authInterceptor = AuthorizationInterceptor()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}

class AuthorizationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("apikey", Constants.omdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}