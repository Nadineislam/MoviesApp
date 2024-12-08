package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.handleAndEmitResponse
import com.example.domain.use_case.MoviesCategoriesUseCase
import com.example.domain.use_case.MovieCategoryUseCase
import com.example.presentation.intents.MoviesIntent
import com.example.presentation.viewstates.MoviesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesCategoriesUseCase: MoviesCategoriesUseCase,
    private val movieCategoryUseCase: MovieCategoryUseCase
) : ViewModel() {
    private val _moviesState = MutableStateFlow<MoviesViewState>(MoviesViewState.Loading)
    val moviesState = _moviesState.asStateFlow()

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

    fun getMoviesCategories() = viewModelScope.launch(Dispatchers.IO) {
        handleAndEmitResponse(
            response = moviesCategoriesUseCase(),
            createSuccessEvent = { data -> MoviesViewState.SuccessMoviesCategories(data) },
            emitEvent = { event -> _moviesState.value = event }
        ) { errorMessage -> MoviesViewState.Error(errorMessage) }
    }

    fun getMovieCategory(page: Int, categoryId: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (isLoading) return@launch
        isLoading = true

        handleAndEmitResponse(
            response = movieCategoryUseCase(page, categoryId),
            createSuccessEvent = { data -> MoviesViewState.SuccessMovieCategory(data) },
            emitEvent = { event -> _moviesState.value = event }
        ) { errorMessage -> MoviesViewState.Error(errorMessage) }

        currentPage += 1
        isLoading = false
        currentCategory = categoryId
    }


}
