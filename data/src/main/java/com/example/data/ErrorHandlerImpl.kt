package com.example.data

import com.example.domain.model.ErrorEntity
import com.example.domain.model.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when(throwable.code()) {
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> ErrorEntity.Network
                    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ErrorEntity.Network
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    else -> ErrorEntity.Unknown
                }
            }
            else -> {
                //  API response
                if (throwable.message == "Movie not found!") {
                    ErrorEntity.NoSearchResult
                } else {
                    ErrorEntity.Unknown
                }
            }
        }
    }
}