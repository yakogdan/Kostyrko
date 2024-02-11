package com.yakogdan.kinopoiskkostyrko.domain.entity

data class Film(
    val kinopoiskId: Int,
    val countries: List<Country>? = listOf(),
    val genres: List<Genre>? = listOf(),
    val imdbId: String? = "",
    val nameEn: String? = "",
    val nameOriginal: String? = "",
    val nameRu: String? = "",
    val posterUrl: String? = "",
    val posterUrlPreview: String? = "",
    val ratingImdb: Double? = 0.0,
    val ratingKinopoisk: Double? = 0.0,
    val type: String? = "",
    val year: Int? = 1111
)