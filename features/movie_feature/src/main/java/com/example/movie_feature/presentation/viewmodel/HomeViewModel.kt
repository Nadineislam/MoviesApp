package com.example.movie_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.use_case.SearchMovieUseCase
import com.example.movie_feature.domain.use_case.TrendingMoviesUseCase
import com.example.movie_feature.domain.use_case.TrendingPeopleUseCase
import com.example.movie_feature.domain.use_case.TrendingTvUseCase
import com.example.movie_feature.presentation.intents.HomeIntent
import com.example.movie_feature.presentation.viewstates.HomeViewState
import com.example.presentation.StateRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendingMoviesUseCase: TrendingMoviesUseCase,
    private val trendingTvUseCase: TrendingTvUseCase,
    private val trendingPeopleUseCase: TrendingPeopleUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    var homeViewState = HomeViewState.Loading

    private val _stateRendererMutableState = MutableStateFlow<StateRenderer<HomeViewState, Any>>(
        StateRenderer.ScreenContent(homeViewState)
    )
    val stateRendererFlow: StateFlow<StateRenderer<HomeViewState, Any>> get() = _stateRendererMutableState

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

    private fun searchMovies(query: String) =
        getTrendingData(
            useCase = searchMovieUseCase,
            onSuccess = { response ->
                _stateRendererMutableState.value =
                    StateRenderer.Success(HomeViewState.SuccessSearchResults(response))
            },
            param = query
        )


    private fun getTrendingMovies() {
        getTrendingData(
            useCase = trendingMoviesUseCase,
            onSuccess = { response ->
                val currentState =
                    (_stateRendererMutableState.value as? StateRenderer.Success)?.output as? HomeViewState.SuccessTrendingData
                _stateRendererMutableState.value = StateRenderer.Success(
                    HomeViewState.SuccessTrendingData(
                        movies = response,
                        tvShows = currentState?.tvShows,
                        people = currentState?.people
                    )
                )
            }
        )
    }

    private fun getTrendingTvShows() {
        getTrendingData(
            useCase = trendingTvUseCase,
            onSuccess = { response ->
                val currentState =
                    (_stateRendererMutableState.value as? StateRenderer.Success)?.output as? HomeViewState.SuccessTrendingData
                _stateRendererMutableState.value = StateRenderer.Success(
                    HomeViewState.SuccessTrendingData(
                        movies = currentState?.movies,
                        tvShows = response,
                        people = currentState?.people
                    )
                )
            }
        )
    }

    private fun getTrendingPeople() {
        getTrendingData(
            useCase = trendingPeopleUseCase,
            onSuccess = { response ->
                val currentState =
                    (_stateRendererMutableState.value as? StateRenderer.Success)?.output as? HomeViewState.SuccessTrendingData
                _stateRendererMutableState.value = StateRenderer.Success(
                    HomeViewState.SuccessTrendingData(
                        movies = currentState?.movies,
                        tvShows = currentState?.tvShows,
                        people = response
                    )
                )
            }
        )
    }


    fun loadAllTrendingData() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesDeferred = async { getTrendingMovies() }
            val tvShowsDeferred = async { getTrendingTvShows() }
            val peopleDeferred = async { getTrendingPeople() }

            moviesDeferred.await()
            tvShowsDeferred.await()
            peopleDeferred.await()

            val currentState =
                (_stateRendererMutableState.value as? StateRenderer.Success)?.output as? HomeViewState.SuccessTrendingData

            _stateRendererMutableState.value = StateRenderer.Success(
                HomeViewState.SuccessTrendingData(
                    movies = currentState?.movies,
                    tvShows = currentState?.tvShows,
                    people = currentState?.people
                )
            )
        }
    }

    fun <T> getTrendingData(
        useCase: AsyncUseCase<T>,
        onSuccess: (T) -> Unit,
        param: String? = null,

        ) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateRendererMutableState.value =
                StateRenderer.LoadingPopup(homeViewState)

            useCase.execute(
                success = { response ->
                    onSuccess(response)
                },
                error = { errorMessage ->
                    _stateRendererMutableState.value =
                        StateRenderer.ErrorFullScreen(homeViewState, errorMessage)
                },
                empty = {
                    _stateRendererMutableState.value = StateRenderer.Empty(homeViewState)
                },
                param = param,

                )
        }
    }

}



