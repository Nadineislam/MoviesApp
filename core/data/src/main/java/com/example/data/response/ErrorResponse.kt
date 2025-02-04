package com.example.data.response


data class ErrorResponse(
    val errorCode: String?,
    val errorMessage: String?,
    val errorFieldList: List<String>?,
)
