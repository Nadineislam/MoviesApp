package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.handleAndEmitResponse
import com.example.domain.use_case.SearchMovieUseCase
import com.example.domain.use_case.TrendingMoviesUseCase
import com.example.domain.use_case.TrendingPeopleUseCase
import com.example.domain.use_case.TrendingTvUseCase
import com.example.presentation.intents.HomeIntent
import com.example.presentation.viewstates.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendingMoviesUseCase: TrendingMoviesUseCase,
    private val trendingTvUseCase: TrendingTvUseCase,
    private val trendingPeopleUseCase: TrendingPeopleUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeState = _homeState.asStateFlow()

    init {
        processIntent(HomeIntent.LoadAllTrendingData)
    }

    fun processIntent(intent: HomeIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is HomeIntent.SearchMovies -> searchMovies(intent.query)
                is HomeIntent.LoadAllTrendingData -> loadAllTrendingData()
            }
        }
    }

    private fun searchMovies(query: String) = viewModelScope.launch(Dispatchers.IO) {
        handleAndEmitResponse(
            response = searchMovieUseCase(query),
            createSuccessEvent = { data -> HomeViewState.SuccessSearchResults(data) },
            emitEvent = { event -> _homeState.value = event },
            onErrorEvent = { errorMessage -> HomeViewState.Error(errorMessage) }
        )
    }

    private fun loadAllTrendingData() = viewModelScope.launch(Dispatchers.IO) {
        val moviesDeferred = async { trendingMoviesUseCase() }
        val tvShowsDeferred = async { trendingTvUseCase() }
        val peopleDeferred = async { trendingPeopleUseCase() }

        val moviesResponse = moviesDeferred.await()
        val tvShowsResponse = tvShowsDeferred.await()
        val peopleResponse = peopleDeferred.await()

        _homeState.value = HomeViewState.Success(
            movies = moviesResponse.body(),
            tvShows = tvShowsResponse.body(),
            people = peopleResponse.body()
        )
    }
}



