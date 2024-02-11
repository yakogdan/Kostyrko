package com.yakogdan.kinopoiskkostyrko.data.mapper

import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.CountryDb
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.FilmDb
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.GenreDb
import com.yakogdan.kinopoiskkostyrko.domain.entity.Country
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.entity.Genre

fun Film.filmToDbModel(): FilmDb = FilmDb(
    kinopoiskId,
    countries.countriesToDbModels(),
    genres.genresToDbModels(),
    imdbId,
    nameEn,
    nameOriginal,
    nameRu,
    posterUrl,
    posterUrlPreview,
    ratingImdb,
    ratingKinopoisk,
    type,
    year
)

fun Country.countryToDbModel(): CountryDb = CountryDb(country)
fun List<Country>.countriesToDbModels(): List<CountryDb> = map { it.countryToDbModel() }

fun Genre.genreToDbModel(): GenreDb = GenreDb(genre)
fun List<Genre>.genresToDbModels(): List<GenreDb> = map { it.genreToDbModel() }

fun FilmDb.filmDbToEntity(): Film = Film(
    kinopoiskId,
    countries.countriesDbToEntities(),
    genres.genresDbToEntities(),
    imdbId,
    nameEn,
    nameOriginal,
    nameRu,
    posterUrl,
    posterUrlPreview,
    ratingImdb,
    ratingKinopoisk,
    type,
    year
)

fun List<FilmDb>.filmsDbToEntities(): List<Film> = map { it.filmDbToEntity() }

fun CountryDb.countryDbToEntity(): Country = Country(country)
fun List<CountryDb>.countriesDbToEntities(): List<Country> = map { it.countryDbToEntity() }

fun GenreDb.genreDbToEntity(): Genre = Genre(genre)
fun List<GenreDb>.genresDbToEntities(): List<Genre> = map { it.genreDbToEntity() }