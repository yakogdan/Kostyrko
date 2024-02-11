package com.yakogdan.kinopoiskkostyrko.domain.entity

data class Film(
    val countries: List<Country>,
    val genres: List<Genre>,
    val imdbId: String,
    val kinopoiskId: Int,
    val nameEn: Any,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingImdb: Double,
    val ratingKinopoisk: Double,
    val type: String,
    val year: Int
)