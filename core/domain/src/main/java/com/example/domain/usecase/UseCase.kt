package com.example.domain.usecase

import com.example.domain.model.ErrorMessage
import com.example.domain.result.OutCome

interface UseCase<R> {

  suspend fun onSuccess(success: OutCome.Success<R>)

  suspend fun onEmpty()

  suspend fun onError(errorMessage: ErrorMessage)
}