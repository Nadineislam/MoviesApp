package com.example.domain.usecase

import com.example.domain.model.ErrorMessage
import com.example.domain.result.OutCome

abstract class AsyncUseCase<R> : UseCase<R> {
    private lateinit var success: suspend (R) -> Unit
    private lateinit var empty: suspend () -> Unit
    private lateinit var error: suspend (ErrorMessage) -> Unit

    suspend fun execute(
        success: suspend (R) -> Unit = {},
        empty: suspend () -> Unit = {},
        error: suspend (ErrorMessage) -> Unit = {},
        param: String? = null,
        page: Int?=0,
        categoryId: Int?=0
    ) {
        this.success = success
        this.empty = empty
        this.error = error

        run(param, page, categoryId).accept(this)

    }


    abstract suspend fun run(param: String?, page: Int?, categoryId: Int?): OutCome<R>


    override suspend fun onSuccess(success: OutCome.Success<R>) {
        success(success.data)
    }

    override suspend fun onEmpty() {
        empty()
    }

    override suspend fun onError(errorMessage: ErrorMessage) {
        error(errorMessage)
    }
}


