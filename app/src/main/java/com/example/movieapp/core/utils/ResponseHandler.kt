package com.example.movieapp.core.utils

import com.example.movieapp.movie_home_feature.presentation.viewstates.TvViewState
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
inline fun <reified T> handleAndEmitTvResponse(
    response: Response<T>,
    createSuccessEvent: (T?) -> TvViewState,
    emitEvent: (TvViewState) -> Unit
) {
    val event = when {
        response.isSuccessful -> {
            response.body()?.let { body ->
                createSuccessEvent(body)
            } ?: TvViewState.Error("Response body is null")
        }
        else -> TvViewState.Error("An error occurred")
    }

    emitEvent(event)
}