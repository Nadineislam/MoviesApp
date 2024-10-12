package com.example.movies_feature

import com.example.base.MainCoroutineExt
import com.example.domain.models.TrendingMoviesResponse
import com.example.domain.models.TrendingPeopleResponse
import com.example.domain.models.TrendingTvResponse
import com.example.domain.use_case.SearchMovieUseCase
import com.example.domain.use_case.TrendingMoviesUseCase
import com.example.domain.use_case.TrendingPeopleUseCase
import com.example.domain.use_case.TrendingTvUseCase
import com.example.presentation.intents.HomeIntent
import com.example.presentation.viewmodel.HomeViewModel
import com.example.presentation.viewstates.HomeViewState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var trendingMoviesUseCase: TrendingMoviesUseCase
    private lateinit var trendingTvUseCase: TrendingTvUseCase
    private lateinit var trendingPeopleUseCase: TrendingPeopleUseCase
    private lateinit var searchMovieUseCase: SearchMovieUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        trendingMoviesUseCase = Mockito.mock(TrendingMoviesUseCase::class.java)
        trendingTvUseCase = Mockito.mock(TrendingTvUseCase::class.java)
        trendingPeopleUseCase = Mockito.mock(TrendingPeopleUseCase::class.java)
        searchMovieUseCase = Mockito.mock(SearchMovieUseCase::class.java)
        viewModel = HomeViewModel(
            trendingMoviesUseCase,
            trendingTvUseCase,
            trendingPeopleUseCase,
            searchMovieUseCase
        )
    }

    @get:Rule
    val mainCoroutineExt = MainCoroutineExt()

    @Test
    fun `when loadTrendingMovies is called with success state then the list of movies should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(
                TrendingMoviesResponse(
                    1,
                    listOf()
                )
            )
            Mockito.`when`(trendingMoviesUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingMovies)

            assertTrue(
                (viewModel.homeState.value as HomeViewState.SuccessMovies).movies == mockedResponse.body()
            )
        }

    @Test
    fun `when loadTrendingMovies is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingMoviesResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingMoviesUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingMovies)

            assertEquals(
                errorMessage,
                (viewModel.homeState.value as HomeViewState.Error).message
            )
        }

    @Test
    fun `when loadTrendingTvShows is called with success state then the list of tv shows should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(
                TrendingTvResponse(
                    1,
                    listOf()
                )
            )
            Mockito.`when`(trendingTvUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingTvShows)

            assertTrue(
                (viewModel.homeState.value as HomeViewState.SuccessTvShows).tvShows == mockedResponse.body()
            )
        }

    @Test
    fun `when loadTrendingTvShows is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingTvUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingTvShows)

            assertEquals(
                errorMessage,
                (viewModel.homeState.value as HomeViewState.Error).message
            )
        }

    @Test
    fun `when loadTrendingPeople is called with success state then the list of people should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(
                TrendingPeopleResponse(
                    1,
                    listOf()
                )
            )
            Mockito.`when`(trendingPeopleUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingPeople)

            assertTrue(
                (viewModel.homeState.value as HomeViewState.SuccessPeople).people == mockedResponse.body()
            )
        }

    @Test
    fun `when loadTrendingPeople is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingPeopleResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingPeopleUseCase()).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.LoadTrendingPeople)

            assertEquals(
                errorMessage,
                (viewModel.homeState.value as HomeViewState.Error).message
            )
        }

    @Test
    fun `when searchMovies is called with success state then the search results should be retrieved`() =
        runBlocking {
            val searchQuery = "query"
            val mockedResponse = Response.success(
                TrendingTvResponse(
                    1,
                    listOf()
                )
            )
            Mockito.`when`(searchMovieUseCase(searchQuery)).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.SearchMovies(searchQuery))

            assertEquals(
                mockedResponse.body(),
                (viewModel.homeState.value as HomeViewState.SuccessSearchResults).searchResults
            )
        }

    @Test
    fun `when searchMovies is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(searchMovieUseCase("query")).thenReturn(mockedResponse)

            viewModel.processIntent(HomeIntent.SearchMovies("query"))

            assertEquals(
                errorMessage,
                (viewModel.homeState.value as HomeViewState.Error).message
            )
        }

}