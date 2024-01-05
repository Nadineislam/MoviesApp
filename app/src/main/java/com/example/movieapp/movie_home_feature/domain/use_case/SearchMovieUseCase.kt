package com.example.movieapp.movie_home_feature.domain.use_case

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(movieName: String): Response<TrendingTvResponse> {
        return repository.getSearchMovie(movieName)
    }
}
