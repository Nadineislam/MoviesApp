package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.handleAndEmitTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoryUseCase
import com.example.movieapp.movie_home_feature.presentation.intents.TvIntent
import com.example.movieapp.movie_home_feature.presentation.viewstates.TvViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvCategoriesUseCase: TvCategoriesUseCase,
    private val tvCategoryUseCase: TvCategoryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<TvViewState>(TvViewState.Loading)
    val state = _state.asStateFlow()

    private var isLoading = false
    var currentCategory: Int? = null
    var currentPage = 1

    init {
        processIntent(TvIntent.FetchTvCategories)
    }

    fun processIntent(intent: TvIntent) {
        viewModelScope.launch {
            when (intent) {
                is TvIntent.FetchTvCategories -> loadTvCategories()
                is TvIntent.FetchTvCategory -> loadTvCategory(intent.page, intent.categoryId)
            }
        }
    }

    fun loadTvCategories() =
        viewModelScope.launch {
            handleAndEmitTvResponse(
                tvCategoriesUseCase(),
                { data -> TvViewState.SuccessTvCategories(data) }
            ) { event ->
                _state.value = event
            }
        }


    fun loadTvCategory(page: Int, categoryId: Int) = viewModelScope.launch {
        if (isLoading) return@launch
        isLoading = true

        handleAndEmitTvResponse(
            tvCategoryUseCase(page, categoryId),
            { data -> TvViewState.SuccessTvCategory(data) }
        ) { event ->
            _state.value = event
        }

        isLoading = false
        currentCategory = categoryId
        currentPage = page + 1

    }
}