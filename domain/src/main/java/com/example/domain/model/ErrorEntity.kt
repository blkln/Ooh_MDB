package com.example.domain.model

sealed class ErrorEntity {
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object NoSearchResult : ErrorEntity()
    object Unknown : ErrorEntity()
}

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}

