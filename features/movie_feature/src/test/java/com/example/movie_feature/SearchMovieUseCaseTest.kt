package com.example.movie_feature

import com.example.domain.model.ErrorMessage
import com.example.domain.result.OutCome
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.domain.repository.HomeRepository
import com.example.movie_feature.domain.use_case.SearchMovieUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.api.mockito.PowerMockito.`when`

@RunWith(MockitoJUnitRunner::class)
class SearchMovieUseCaseTest {

    @Mock
    private lateinit var repository: HomeRepository

    private lateinit var searchMovieUseCase: SearchMovieUseCase

    @Before
    fun setup() {
        searchMovieUseCase = SearchMovieUseCase(repository)
    }

    @Test
    fun `when searchMovieUseCase returns empty, execute should call onEmpty`() = runTest {
        val query = "dummy_query"
        `when`(repository.getSearchMovie(query)).thenReturn(OutCome.empty())

        var emptyCalled = false

        searchMovieUseCase.execute(
            success = {},
            empty = { emptyCalled = true },
            error = {},
            param = query
        )

        assertTrue(emptyCalled)
    }
    @Test
    fun `when searchMovieUseCase returns success, execute should call onSuccess`() = runTest {
        val query = "dummy_query"
        val mockResponse = TrendingTvResponse(1, listOf())
        `when`(repository.getSearchMovie(query)).thenReturn(OutCome.success(mockResponse))

        var receivedResponse: TrendingTvResponse? = null

        searchMovieUseCase.execute(
            success = { response ->
                receivedResponse = response
            },
            empty = {},
            error = {},
            param = query
        )

        assertEquals(mockResponse, receivedResponse)
    }
    @Test
    fun `when searchMovieUseCase returns error, execute should call onError`() = runTest {
        val query = "dummy_query"
        val errorMessage = ErrorMessage(500,"Something went wrong", emptyList())
        `when`(repository.getSearchMovie(query)).thenReturn(OutCome.error(errorMessage))

        var receivedError: ErrorMessage? = null

        searchMovieUseCase.execute(
            success = {},
            empty = {},
            error = { error ->
                receivedError = error
            },
            param = query
        )

        assertEquals(errorMessage, receivedError)
    }
}







