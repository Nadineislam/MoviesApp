package com.example.movie_feature.presentation.components
import kotlinx.serialization.Serializable
@Serializable
data class MoviesCategoryScreen(val categoryId: Int)
@Serializable
data class TvCategoryScreen(val categoryId: Int)
@Serializable
data class PeopleDetailsScreen(val personName: String, val personPoster: String)
@Serializable
data class MovieDetailsScreen(val movieName: String, val moviePoster: String, val movieOverview: String, val movieVote: String)