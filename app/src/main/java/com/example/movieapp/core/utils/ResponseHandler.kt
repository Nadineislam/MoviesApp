package com.example.movieapp.core.utils


import retrofit2.Response

inline fun <T, R> handleAndEmitResponse(
    response: Response<T>,
    createSuccessEvent: (T?) -> R,
    emitEvent: (R) -> Unit,
    onErrorEvent: (String) -> R
) {
    val event = when {
        response.isSuccessful -> {
            response.body()?.let { body ->
                createSuccessEvent(body)
            } ?: onErrorEvent("Response body is null")
        }

        else -> onErrorEvent("An error occurred")
    }

    emitEvent(event)
}