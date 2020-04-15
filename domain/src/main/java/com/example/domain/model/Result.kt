package com.example.domain.model

sealed class Result<out T: Any> {
    data class Success<T : Any>(val data: T) : Result<T>()
    data class Error<T : Any>(val error: ErrorEntity) : Result<T>()
}
