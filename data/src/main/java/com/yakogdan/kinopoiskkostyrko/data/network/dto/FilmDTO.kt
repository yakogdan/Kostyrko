package com.yakogdan.kinopoiskkostyrko.data.network.dto

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int,
    @SerializedName("countries")
    val countries: List<CountryDTO>? = listOf(),
    @SerializedName("genres")
    val genres: List<GenreDTO>? = listOf(),
    @SerializedName("imdbId")
    val imdbId: String? = "",
    @SerializedName("nameEn")
    val nameEn: String? = "",
    @SerializedName("nameOriginal")
    val nameOriginal: String? = "",
    @SerializedName("nameRu")
    val nameRu: String? = "",
    @SerializedName("posterUrl")
    val posterUrl: String? = "",
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String? = "",
    @SerializedName("ratingImdb")
    val ratingImdb: Double? = 0.0,
    @SerializedName("ratingKinopoisk")
    val ratingKinopoisk: Double? = 0.0,
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("year")
    val year: Int? = 1111
)