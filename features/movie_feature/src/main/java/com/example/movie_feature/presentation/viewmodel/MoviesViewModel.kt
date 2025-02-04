package com.example.movie_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.use_case.MovieCategoryUseCase
import com.example.movie_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movie_feature.presentation.intents.MoviesIntent
import com.example.movie_feature.presentation.viewstates.MoviesViewState
import com.example.presentation.StateRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesCategoriesUseCase: MoviesCategoriesUseCase,
    private val movieCategoryUseCase: MovieCategoryUseCase
) : ViewModel() {
    private val moviesViewState = MoviesViewState.Loading

    private val _stateRendererMutableState = MutableStateFlow<StateRenderer<MoviesViewState, Any>>(
        StateRenderer.ScreenContent(MoviesViewState.Loading)
    )
    val stateRendererFlow: StateFlow<StateRenderer<MoviesViewState, Any>> get() = _stateRendererMutableState

    var currentPage = 1
    private var isLoading = false
    private var currentCategory: Int? = null

    init {
        processIntent(MoviesIntent.FetchMoviesCategories)
    }

    fun processIntent(intent: MoviesIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is MoviesIntent.FetchMoviesCategories -> getMoviesCategories()
                is MoviesIntent.FetchMovieCategory -> getMovieCategory(
                    intent.page,
                    intent.categoryId
                )
            }
        }
    }

    private fun getMoviesCategories() = getTrendingData(
        useCase = moviesCategoriesUseCase,
        onSuccess = { response ->
            _stateRendererMutableState.value = StateRenderer.Success(MoviesViewState.SuccessMoviesCategories(response))
        }
    )

    private fun getMovieCategory(page: Int, categoryId: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (isLoading) return@launch
        isLoading = true

       getTrendingData(
            useCase = movieCategoryUseCase,
            onSuccess = { response ->
                _stateRendererMutableState.value = StateRenderer.Success(MoviesViewState.SuccessMovieCategory(response))
            },
            page = page,
            categoryId = categoryId
        )

        currentPage += 1
        isLoading = false
        currentCategory = categoryId
    }
    private fun <T> getTrendingData(
        useCase: AsyncUseCase<T>,
        onSuccess: (T) -> Unit,
        page: Int? = 0,
        categoryId: Int?=0
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateRendererMutableState.value = StateRenderer.LoadingPopup(MoviesViewState.Loading)

            useCase.execute(
                success = { response ->
                    onSuccess(response)
                },
                error = { errorMessage ->
                    _stateRendererMutableState.value =
                        StateRenderer.ErrorFullScreen(moviesViewState, errorMessage)
                },
                empty = {
                    _stateRendererMutableState.value = StateRenderer.Empty(moviesViewState)
                },
                page = page,
                categoryId = categoryId
            )
        }
    }


}
