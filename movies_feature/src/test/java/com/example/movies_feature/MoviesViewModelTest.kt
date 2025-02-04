package com.example.movies_feature
//
//import com.example.base.MainCoroutineExt
//import com.example.domain.models.CategoriesResponse
//import com.example.domain.models.TrendingMoviesResponse
//import com.example.domain.use_case.MovieCategoryUseCase
//import com.example.domain.use_case.MoviesCategoriesUseCase
//import com.example.presentation.viewmodel.MoviesViewModel
//import com.example.presentation.viewstates.MoviesViewState
//import junit.framework.TestCase.assertEquals
//import junit.framework.TestCase.assertTrue
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import okhttp3.ResponseBody.Companion.toResponseBody
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito
//import retrofit2.Response
//
//@ExperimentalCoroutinesApi
//class MoviesViewModelTest {
//
//    private lateinit var movieCategoryUseCase: MovieCategoryUseCase
//    private lateinit var moviesCategoriesUseCase: MoviesCategoriesUseCase
//    private lateinit var viewModel: MoviesViewModel
//
//    @Before
//    fun setup() {
//        movieCategoryUseCase = Mockito.mock(MovieCategoryUseCase::class.java)
//        moviesCategoriesUseCase = Mockito.mock(MoviesCategoriesUseCase::class.java)
//        viewModel = MoviesViewModel(
//            moviesCategoriesUseCase,
//            movieCategoryUseCase
//        )
//    }
//
//    @get:Rule
//    val mainCoroutineExt = MainCoroutineExt()
//
//    @Test
//    fun `when getMoviesCategories is called with success state then the list of movie categories should be retrieved`() =
//        runBlocking {
//            val mockedResponse = Response.success(
//                CategoriesResponse(
//                    listOf()
//                )
//            )
//            Mockito.`when`(moviesCategoriesUseCase()).thenReturn(mockedResponse)
//
//            viewModel.getMoviesCategories()
//
//            assertTrue(
//                (viewModel.moviesState.value as MoviesViewState.SuccessMoviesCategories)
//                    .categories == mockedResponse.body()
//            )
//        }
//
//    @Test
//    fun `when getMoviesCategories is called with failure state then error should be retrieved`() =
//        runBlocking {
//            val errorMessage = "An error occurred"
//            val mockedResponse =
//                Response.error<CategoriesResponse>(400, errorMessage.toResponseBody(null))
//            Mockito.`when`(moviesCategoriesUseCase()).thenReturn(mockedResponse)
//
//            viewModel.getMoviesCategories()
//
//            assertEquals(
//                errorMessage,
//                (viewModel.moviesState.value as MoviesViewState.Error).message
//            )
//        }
//
//    @Test
//    fun `when getMovieCategory is called with success state then the list of movie categories should be retrieved`() =
//        runBlocking {
//            val categoryId = 1
//            val page = 1
//            val mockedResponse = Response.success(
//                TrendingMoviesResponse(
//                    0,
//                    listOf()
//                )
//            )
//            Mockito.`when`(movieCategoryUseCase(page, categoryId)).thenReturn(mockedResponse)
//
//            viewModel.getMovieCategory(page, categoryId)
//
//            assertTrue(
//                (viewModel.moviesState.value as MoviesViewState.SuccessMovieCategory)
//                    .category == mockedResponse.body()
//            )
//        }
//
//    @Test
//    fun `when getMovieCategory is called with failure state then error should be retrieved`() =
//        runBlocking {
//            val categoryId = 1
//            val page = 1
//            val errorMessage = "An error occurred"
//            val mockedResponse =
//                Response.error<TrendingMoviesResponse>(400, errorMessage.toResponseBody(null))
//            Mockito.`when`(movieCategoryUseCase(page, categoryId)).thenReturn(mockedResponse)
//
//            viewModel.getMovieCategory(page, categoryId)
//
//            assertEquals(
//                errorMessage,
//                (viewModel.moviesState.value as MoviesViewState.Error).message
//            )
//        }
//}