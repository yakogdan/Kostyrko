package com.yakogdan.kinopoiskkostyrko.data.local.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yakogdan.kinopoiskkostyrko.data.local.db.Converters

@Entity(tableName = "favourite_films")
@TypeConverters(Converters::class)
data class FilmDb(
    @PrimaryKey
    @ColumnInfo(name = "kinopoiskId")
    val kinopoiskId: Int,
    @ColumnInfo(name = "countries")
    val countries: List<CountryDb>,
    @ColumnInfo(name = "genres")
    val genres: List<GenreDb>,
    @ColumnInfo(name = "imdbId")
    val imdbId: String,
    @ColumnInfo(name = "nameEn")
    val nameEn: String,
    @ColumnInfo(name = "nameOriginal")
    val nameOriginal: String,
    @ColumnInfo(name = "nameRu")
    val nameRu: String,
    @ColumnInfo(name = "posterUrl")
    val posterUrl: String,
    @ColumnInfo(name = "posterUrlPreview")
    val posterUrlPreview: String,
    @ColumnInfo(name = "ratingImdb")
    val ratingImdb: Double,
    @ColumnInfo(name = "ratingKinopoisk")
    val ratingKinopoisk: Double,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "year")
    val year: Int
)