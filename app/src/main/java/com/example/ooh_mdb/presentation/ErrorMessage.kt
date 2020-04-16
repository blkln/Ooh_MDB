package com.example.ooh_mdb.presentation

import com.example.data.Constants
import com.example.domain.model.ErrorEntity

class ErrorMessage(private val error: ErrorEntity) {

    fun generate(): String {
        return when (error) {
            ErrorEntity.Network -> Constants.ERROR_NETWORK
            ErrorEntity.NotFound -> Constants.ERROR_NOT_FOUND
            ErrorEntity.AccessDenied ->Constants.ERROR_ACCESS_DENIED
            ErrorEntity.ServiceUnavailable -> Constants.ERROR_SERVICE_UNAVAILABLE
            ErrorEntity.NoSearchResult -> Constants.ERROR_NO_SEARCH_RESULTS
            ErrorEntity.Unknown -> Constants.ERROR_UNKNOWN
        }
    }
}
