package com.example.data.model

import com.squareup.moshi.Json

class ApiResponse<T> (
    @field:Json(name = "Response")
    val response: String,
    @field:Json(name = "Error")
    val error: String?,
    @field:Json(name = "Search")
    val search: T
)
