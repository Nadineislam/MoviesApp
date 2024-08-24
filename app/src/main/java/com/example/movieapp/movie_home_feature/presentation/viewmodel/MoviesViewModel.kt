package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.handleAndEmitResponse
import com.example.movieapp.movie_home_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.MovieCategoryUseCase
import com.example.movieapp.movie_home_feature.presentation.intents.MoviesIntent
import com.example.movieapp.movie_home_feature.presentation.viewstates.MoviesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    var currentCategory: Int? = null

    init {
        processIntent(MoviesIntent.FetchMoviesCategories)
    }

    fun processIntent(intent: MoviesIntent) {
        viewModelScope.launch {
            when (intent) {
                is MoviesIntent.FetchMoviesCategories -> getMoviesCategories()
                is MoviesIntent.FetchMovieCategory -> getMovieCategory(
                    intent.page,
                    intent.categoryId
                )
            }
        }
    }

    fun getMoviesCategories() = viewModelScope.launch {
        handleAndEmitResponse(
            response = moviesCategoriesUseCase(),
            createSuccessEvent = { data -> MoviesViewState.SuccessMoviesCategories(data) },
            emitEvent = { event -> _moviesState.value = event }
        ) { errorMessage -> MoviesViewState.Error(errorMessage) }
    }

    fun getMovieCategory(page: Int, categoryId: Int) = viewModelScope.launch {
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