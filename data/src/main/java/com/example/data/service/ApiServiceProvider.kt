package com.example.data.service

interface ApiServiceProvider {
    fun create(): OMDbApi
}