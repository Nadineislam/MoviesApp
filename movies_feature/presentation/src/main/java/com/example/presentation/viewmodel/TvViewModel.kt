package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.handleAndEmitResponse
import com.example.domain.use_case.TvCategoriesUseCase
import com.example.domain.use_case.TvCategoryUseCase
import com.example.presentation.intents.TvIntent
import com.example.presentation.viewstates.TvViewState
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

    fun loadTvCategories() = viewModelScope.launch {
        handleAndEmitResponse(
            response = tvCategoriesUseCase(),
            createSuccessEvent = { data -> TvViewState.SuccessTvCategories(data) },
            emitEvent = { event -> _state.value = event },
        ) { errorMessage -> TvViewState.Error(errorMessage) }
    }

    fun loadTvCategory(page: Int, categoryId: Int) = viewModelScope.launch {
        if (isLoading) return@launch
        isLoading = true

        handleAndEmitResponse(
            response = tvCategoryUseCase(page, categoryId),
            createSuccessEvent = { data -> TvViewState.SuccessTvCategory(data) },
            emitEvent = { event -> _state.value = event },
        ) { errorMessage -> TvViewState.Error(errorMessage) }

        isLoading = false
        currentCategory = categoryId
        currentPage = page + 1
    }
}