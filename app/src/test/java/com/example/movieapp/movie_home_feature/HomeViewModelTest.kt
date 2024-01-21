package com.example.movieapp.movie_home_feature

import com.example.movieapp.base.MainCoroutineExt
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.SearchMovieUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingMoviesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingPeopleUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingTvUseCase
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel
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
    fun `when getTrendingMovies is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(TrendingMoviesResponse(1, listOf()))
            Mockito.`when`(trendingMoviesUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingMovies()

            assertTrue(
                (viewModel.movies.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getTrendingMovies is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingMoviesResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingMoviesUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingMovies()

            assertEquals(
                errorMessage,
                (viewModel.movies.value as Resource.Error).message
            )

        }

    @Test
    fun `when getTrendingTv is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(TrendingTvResponse(1, listOf()))
            Mockito.`when`(trendingTvUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingTv()

            assertTrue(
                (viewModel.tv.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getTrendingTv is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingTvUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingTv()

            assertEquals(
                errorMessage,
                (viewModel.tv.value as Resource.Error).message
            )

        }

    @Test
    fun `when getTrendingPeople is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(TrendingPeopleResponse(1, listOf()))
            Mockito.`when`(trendingPeopleUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingPeople()

            assertTrue(
                (viewModel.people.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getTrendingPeople is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingPeopleResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(trendingPeopleUseCase()).thenReturn(mockedResponse)

            viewModel.getTrendingPeople()

            assertEquals(
                errorMessage,
                (viewModel.people.value as Resource.Error).message
            )

        }

    @Test
    fun `when getSearchedMovie is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val searchQuery = "query"
            val mockedResponse = Response.success(TrendingTvResponse(1, listOf()))
            Mockito.`when`(searchMovieUseCase(searchQuery)).thenReturn(mockedResponse)

            viewModel.getSearchedMovie(searchQuery)

            assertEquals(
                mockedResponse.body(),
                (viewModel.searchMovie.value as Resource.Success).data
            )
        }

    @Test
    fun `when getSearchedMovie is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val response =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(searchMovieUseCase("query")).thenReturn(response)

            val searchQuery = "query"
            viewModel.getSearchedMovie(searchQuery)

            assertEquals(
                errorMessage,
                (viewModel.searchMovie.value as Resource.Error).message
            )
        }

}