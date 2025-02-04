package com.example.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.domain.model.ErrorMessage
import com.example.presentation.views.RenderEmptyScreen
import com.example.presentation.views.RenderErrorFullScreen
import com.example.presentation.views.RenderErrorPopup
import com.example.presentation.views.RenderLoadingFullScreen
import com.example.presentation.views.RenderLoadingPopup

sealed class StateRenderer<out S, O> {
    // S for ViewState and O for Output

    class ScreenContent<S, O>(val viewState: S) : StateRenderer<S, O>()

    data class LoadingPopup<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading,
    ) : StateRenderer<S, O>()

    data class LoadingFullScreen<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading,
    ) : StateRenderer<S, O>()


    data class ErrorPopup<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage,
    ) : StateRenderer<S, O>()

    data class ErrorFullScreen<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage,
    ) : StateRenderer<S, O>()

    data class Empty<S, O>(
        val viewState: S,
        @StringRes val emptyMessage: Int = R.string.no_data,
    ) : StateRenderer<S, O>()

    data class Success<S, O>(val output: O) : StateRenderer<S, O>()


    @Composable
    fun onUiState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ScreenContent) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onLoadingState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is LoadingPopup) {
            action(viewState)
        } else if (this is LoadingFullScreen) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onSuccessState(action: @Composable (O) -> Unit): StateRenderer<S, O> {
        if (this is Success) {
            action(output)
        }
        return this
    }

    @Composable
    fun onErrorState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ErrorPopup) {
            action(viewState)
        } else if (this is ErrorFullScreen) {
            action(viewState)
        }
        return this
    }

    fun onEmptyState(action: () -> Unit): StateRenderer<S, O> {
        if (this is Empty) {
            action()
        }
        return this
    }

    companion object {
        @Composable
        fun <S, O> of(
            retryAction: () -> Unit = {},
            statRenderer: StateRenderer<S, O>,
            blocK: @Composable StateRenderer<S, O>.() -> Unit,
        ): StateRenderer<S, O> {
            statRenderer.blocK() // show this first before doing any thing

            when (statRenderer) {
                is Empty -> RenderEmptyScreen(statRenderer.emptyMessage)
                is ErrorFullScreen -> RenderErrorFullScreen(statRenderer.errorMessage, retryAction)
                is ErrorPopup -> RenderErrorPopup(statRenderer.errorMessage, retryAction)
                is LoadingFullScreen -> RenderLoadingFullScreen(statRenderer.loadingMessage)
                is LoadingPopup -> RenderLoadingPopup(statRenderer.loadingMessage)
                else -> {}
            }
            return statRenderer
        }
    }
}