package com.example.movieapp.movie_home_feature

import com.example.movieapp.base.MainCoroutineExt
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoryUseCase
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel
import com.example.movieapp.movie_home_feature.presentation.viewstates.TvViewState
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

@ExperimentalCoroutinesApi
class TvViewModelTest {
    private lateinit var tvCategoryUseCase: TvCategoryUseCase
    private lateinit var tvCategoriesUseCase: TvCategoriesUseCase
    private lateinit var viewModel: TvViewModel

    @Before
    fun setup() {
        tvCategoryUseCase = Mockito.mock(TvCategoryUseCase::class.java)
        tvCategoriesUseCase = Mockito.mock(TvCategoriesUseCase::class.java)
        viewModel = TvViewModel(tvCategoriesUseCase, tvCategoryUseCase)
    }

    @get:Rule
    val mainCoroutineExt = MainCoroutineExt()

    @Test
    fun `when loadTvCategories is called with success state then the list of tv categories should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(CategoriesResponse(listOf()))
            Mockito.`when`(tvCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.loadTvCategories()

            assertTrue(
                (viewModel.state.value as TvViewState.SuccessTvCategories)
                    .categories == mockedResponse.body()
            )
        }

    @Test
    fun `when loadTvCategories is called with failure state then error should be retrieved`() =
        runBlocking {
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<CategoriesResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(tvCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.loadTvCategories()

            assertEquals(
                errorMessage,
                (viewModel.state.value as TvViewState.Error).message
            )

        }

    @Test
    fun `when loadTvCategory is called with success state then the list of tv category should be retrieved`() =
        runBlocking {
            val categoryId = 1
            val page = 1
            val mockedResponse = Response.success(TrendingTvResponse(0, listOf()))
            Mockito.`when`(tvCategoryUseCase(page, categoryId)).thenReturn(mockedResponse)

            viewModel.loadTvCategory(categoryId, page)

            assertTrue(
                (viewModel.state.value as TvViewState.SuccessTvCategory)
                    .tvCategories == mockedResponse.body()
            )
        }

    @Test
    fun `when loadTvCategory is called with failure state then error should be retrieved`() =
        runBlocking {
            val categoryId = 1
            val page = 1
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(tvCategoryUseCase(page, categoryId)).thenReturn(mockedResponse)

            viewModel.loadTvCategory(categoryId, page)

            assertEquals(
                errorMessage,
                (viewModel.state.value as TvViewState.Error).message
            )

        }
}