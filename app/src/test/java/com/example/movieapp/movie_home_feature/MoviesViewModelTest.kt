package com.example.movieapp.movie_home_feature

import com.example.movieapp.base.MainCoroutineExt
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.domain.use_case.MovieCategoryUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var movieCategoryUseCase: MovieCategoryUseCase
    private lateinit var moviesCategoriesUseCase: MoviesCategoriesUseCase
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        movieCategoryUseCase = Mockito.mock(MovieCategoryUseCase::class.java)
        moviesCategoriesUseCase = Mockito.mock(MoviesCategoriesUseCase::class.java)
        viewModel = MoviesViewModel(moviesCategoriesUseCase, movieCategoryUseCase)
    }

    @get:Rule
    val mainCoroutineExt = MainCoroutineExt()

    @Test
    fun `when getCategories is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(CategoriesResponse( listOf()))
            Mockito.`when`(moviesCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.getCategories()

            TestCase.assertTrue(
                (viewModel.categories.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getCategories is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<CategoriesResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(moviesCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.getCategories()

            TestCase.assertEquals(
                errorMessage,
                (viewModel.categories.value as Resource.Error).message
            )

        }
    @Test
    fun `when getMovieCategories is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val id=1
            val mockedResponse = Response.success(TrendingMoviesResponse(0, listOf()))
            Mockito.`when`(movieCategoryUseCase(id)).thenReturn(mockedResponse)

            viewModel.getMovieCategories(id)

            TestCase.assertTrue(
                (viewModel.movieCategories.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getMovieCategories is called with failure state then error should be retrieved`() =
        runBlocking {
            val id=1
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingMoviesResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(movieCategoryUseCase(id)).thenReturn(mockedResponse)

            viewModel.getMovieCategories(id)

            TestCase.assertEquals(
                errorMessage,
                (viewModel.movieCategories.value as Resource.Error).message
            )

        }
}