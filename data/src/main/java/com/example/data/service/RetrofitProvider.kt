package com.example.data.service

import com.example.data.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitProvider: ApiServiceProvider {

    private val authInterceptor = AuthorizationInterceptor()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.OMDB_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    override fun create(): OMDbApi {
        return this.retrofit().create(OMDbApi::class.java)
    }

}

class AuthorizationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("apikey", Constants.OMDB_API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}