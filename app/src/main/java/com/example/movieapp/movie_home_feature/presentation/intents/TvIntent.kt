package com.example.movieapp.movie_home_feature.presentation.intents

sealed class TvIntent {
    object FetchTvCategories : TvIntent()
    data class FetchTvCategory( val page: Int,val categoryId: Int) : TvIntent()
}
