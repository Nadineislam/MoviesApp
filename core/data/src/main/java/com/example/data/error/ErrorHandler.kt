package com.example.data.error

import com.example.data.response.ErrorResponse
import com.google.gson.Gson

// create default error response

fun getDefaultErrorResponse() = ErrorResponse("", "", emptyList())

// getting error response from error body "string"

fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
    try {
        gson.fromJson(errorBodyString, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }