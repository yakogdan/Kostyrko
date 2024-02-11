package com.yakogdan.kinopoiskkostyrko.data.local.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yakogdan.kinopoiskkostyrko.data.local.db.Converters

@Entity(tableName = "favourite_films")
@TypeConverters(Converters::class)
data class FilmDb(
    @PrimaryKey val kinopoiskId: Int,
    val countries: List<CountryDb>,
    val genres: List<GenreDb>,
    val imdbId: String,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingImdb: Double,
    val ratingKinopoisk: Double,
    val type: String,
    val year: Int
)