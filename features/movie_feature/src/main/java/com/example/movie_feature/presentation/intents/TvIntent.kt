package com.example.movie_feature.presentation.intents

sealed interface TvIntent {
    object FetchTvCategories : TvIntent
    data class FetchTvCategory( val page: Int,val categoryId: Int) : TvIntent
}
