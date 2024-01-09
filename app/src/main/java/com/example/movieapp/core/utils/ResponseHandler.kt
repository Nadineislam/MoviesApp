package com.example.movieapp.core.utils

import retrofit2.Response

inline fun <reified T> handleResponse(response: Response<T>): Resource<T> {
    return if (response.isSuccessful) {
        response.body()?.let { resultResponse ->
            Resource.Success(resultResponse)
        } ?: Resource.Error("Response body is null")
    } else {
        Resource.Error("An error occurred")
    }
}