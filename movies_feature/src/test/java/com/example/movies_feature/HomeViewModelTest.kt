//package com.example.movies_feature
//
//import com.example.base.MainCoroutineExt
//import com.example.domain.models.TrendingTvResponse
//import com.example.domain.use_case.SearchMovieUseCase
//import com.example.domain.use_case.TrendingMoviesUseCase
//import com.example.domain.use_case.TrendingPeopleUseCase
//import com.example.domain.use_case.TrendingTvUseCase
//import com.example.presentation.intents.HomeIntent
//import com.example.presentation.viewmodel.HomeViewModel
//import com.example.presentation.viewstates.HomeViewState
//import junit.framework.TestCase.assertEquals
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import okhttp3.ResponseBody.Companion.toResponseBody
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito
//import retrofit2.Response
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class HomeViewModelTest {
//    private lateinit var trendingMoviesUseCase: TrendingMoviesUseCase
//    private lateinit var trendingTvUseCase: TrendingTvUseCase
//    private lateinit var trendingPeopleUseCase: TrendingPeopleUseCase
//    private lateinit var searchMovieUseCase: SearchMovieUseCase
//    private lateinit var viewModel: HomeViewModel
//
//    @Before
//    fun setup() {
//        searchMovieUseCase = Mockito.mock(SearchMovieUseCase::class.java)
//        viewModel = HomeViewModel(
//            trendingMoviesUseCase,
//            trendingTvUseCase,
//            trendingPeopleUseCase,
//            searchMovieUseCase
//        )
//    }
//
//    @get:Rule
//    val mainCoroutineExt = MainCoroutineExt()
//
//    @Test
//    fun `when searchMovies is called with success state then the search results should be retrieved`() =
//        runBlocking {
//            val searchQuery = "query"
//            val mockedResponse = Response.success(
//                TrendingTvResponse(
//                    1,
//                    listOf()
//                )
//            )
//            Mockito.`when`(searchMovieUseCase(searchQuery)).thenReturn(mockedResponse)
//
//            viewModel.processIntent(HomeIntent.SearchMovies(searchQuery))
//
//            assertEquals(
//                mockedResponse.body(),
//                (viewModel.homeState.value as HomeViewState.SuccessSearchResults).searchResults
//            )
//        }
//
//    @Test
//    fun `when searchMovies is called with failure state then error should be retrieved`() =
//        runBlocking {
//            val errorMessage = "An error occurred"
//            val mockedResponse =
//                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
//            Mockito.`when`(searchMovieUseCase("query")).thenReturn(mockedResponse)
//
//            viewModel.processIntent(HomeIntent.SearchMovies("query"))
//
//            assertEquals(
//                errorMessage,
//                (viewModel.homeState.value as HomeViewState.Error).message
//            )
//        }
//
//}