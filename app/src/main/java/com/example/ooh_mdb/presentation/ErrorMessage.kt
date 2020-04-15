package com.example.ooh_mdb.presentation

import com.example.domain.model.ErrorEntity

class ErrorMessage(private val error: ErrorEntity) {

    fun generate(): String {
        return when (error) {
            ErrorEntity.Network -> "Network error occurred"
            ErrorEntity.NotFound -> "Resource not found"
            ErrorEntity.AccessDenied -> "Access denied"
            ErrorEntity.ServiceUnavailable -> "Service is unavailable"
            ErrorEntity.NoSearchResult -> "Movie not found!"
            ErrorEntity.Unknown -> "Unknown error"
        }
    }
}

/*
<string name="network">Network error occurred</string>
<string name="not_found">Resource not found</string>
<string name="access_denied">Access denied</string>
<string name="serviceu_navailable">Service is unavailable</string>
<string name="unknown">Unknown error</string>
<string name="network">Network error occurred</string>
*/
