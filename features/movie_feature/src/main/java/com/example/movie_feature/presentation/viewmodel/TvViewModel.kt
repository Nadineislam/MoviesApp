package com.example.movie_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.use_case.TvCategoriesUseCase
import com.example.movie_feature.domain.use_case.TvCategoryUseCase
import com.example.movie_feature.presentation.intents.TvIntent
import com.example.movie_feature.presentation.viewstates.TvViewState
import com.example.presentation.StateRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvCategoriesUseCase: TvCategoriesUseCase,
    private val tvCategoryUseCase: TvCategoryUseCase
) : ViewModel() {
    private val tvViewState = TvViewState.Loading

    private val _stateRendererMutableState = MutableStateFlow<StateRenderer<TvViewState, Any>>(
        StateRenderer.ScreenContent(TvViewState.Loading)
    )
    val stateRendererFlow: StateFlow<StateRenderer<TvViewState, Any>> get() = _stateRendererMutableState

    private var isLoading = false
    var currentCategory: Int? = null
    var currentPage = 1

    init {
        processIntent(TvIntent.FetchTvCategories)
    }

    fun processIntent(intent: TvIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is TvIntent.FetchTvCategories -> loadTvCategories()
                is TvIntent.FetchTvCategory -> loadTvCategory(intent.page, intent.categoryId)
            }
        }
    }

    private fun loadTvCategories()  = getTrendingData(
        useCase = tvCategoriesUseCase,
        onSuccess = { response ->
            _stateRendererMutableState.value = StateRenderer.Success(TvViewState.SuccessTvCategories(response))
        }
    )

    private fun loadTvCategory(page: Int, categoryId: Int) {
        if (isLoading) return
        isLoading = true

        getTrendingData(
            useCase = tvCategoryUseCase,
            onSuccess = { response ->
                _stateRendererMutableState.value = StateRenderer.Success(TvViewState.SuccessTvCategory(response))
            },
            page = page,
            categoryId = categoryId
        )

        isLoading = false
        currentCategory = categoryId
        currentPage = page + 1
    }

    private fun <T> getTrendingData(
        useCase: AsyncUseCase<T>,
        onSuccess: (T) -> Unit,
        page: Int? = 0,
        categoryId: Int?=0
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateRendererMutableState.value = StateRenderer.LoadingPopup(TvViewState.Loading)

           useCase.execute(
               success = { response ->
                   onSuccess(response)
               },
               error = { errorMessage ->
                   _stateRendererMutableState.value =
                       StateRenderer.ErrorFullScreen(tvViewState, errorMessage)
               },
               empty = {
                   _stateRendererMutableState.value = StateRenderer.Empty(tvViewState)
               },
                page = page,
                categoryId = categoryId
           )
        }
    }
}
