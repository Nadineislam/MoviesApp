package com.example.data.mapper

import com.example.data.response.ErrorResponse
import com.example.domain.model.ErrorMessage

fun ErrorResponse.toDomain(code: Int): ErrorMessage {
  return ErrorMessage(
    code = code,
    message = errorMessage.orEmpty(),
    errorFieldList = errorFieldList ?: emptyList(),
  )
}
