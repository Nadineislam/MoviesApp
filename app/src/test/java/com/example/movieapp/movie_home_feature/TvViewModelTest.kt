package com.example.movieapp.movie_home_feature

import com.example.movieapp.base.MainCoroutineExt
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoryUseCase
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel
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
    fun `when getTvCategories is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val id = 1
            val mockedResponse = Response.success(TrendingTvResponse(0, listOf()))
            Mockito.`when`(tvCategoryUseCase(1,id)).thenReturn(mockedResponse)

            viewModel.getTvCategories(id)

            assertTrue(
                (viewModel.tvCategory.value as Resource.Success)
                    .data == mockedResponse.body()
            )
        }

    @Test
    fun `when getTvCategories is called with failure state then error should be retrieved`() =
        runBlocking {
            val id = 1
            val errorMessage = "An error occurred"
            val mockedResponse =
                Response.error<TrendingTvResponse>(400, errorMessage.toResponseBody(null))
            Mockito.`when`(tvCategoryUseCase(1,id)).thenReturn(mockedResponse)

            viewModel.getTvCategories(id)

            assertEquals(
                errorMessage,
                (viewModel.tvCategory.value as Resource.Error).message
            )

        }

    @Test
    fun `when getCategories is called with success state then the list of meals should be retrieved`() =
        runBlocking {
            val mockedResponse = Response.success(CategoriesResponse(listOf()))
            Mockito.`when`(tvCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.getCategories()

            assertTrue(
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
            Mockito.`when`(tvCategoriesUseCase()).thenReturn(mockedResponse)

            viewModel.getCategories()

            assertEquals(
                errorMessage,
                (viewModel.categories.value as Resource.Error).message
            )

        }
}