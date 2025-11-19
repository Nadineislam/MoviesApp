package com.example.movie_feature.data.mappers

import com.example.movie_feature.data.remote.models.MoviesDto
import com.example.movie_feature.data.remote.models.TrendingMoviesDtoResponse
import com.example.movie_feature.domain.models.Movies
import com.example.movie_feature.domain.models.TrendingMoviesResponse

fun TrendingMoviesDtoResponse.toMoviesModel(): TrendingMoviesResponse {
    return TrendingMoviesResponse(
        page = this.page,
        trendingMovies = this.trendingMovies.map { it.toMovies() }
    )
}

fun MoviesDto.toMovies(): Movies {
    return Movies(
        name = this.name,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        overView = this.overView,
        logo = this.logo
    )
}