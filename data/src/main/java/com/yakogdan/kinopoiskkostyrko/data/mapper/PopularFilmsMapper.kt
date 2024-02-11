package com.yakogdan.kinopoiskkostyrko.data.mapper

import com.yakogdan.kinopoiskkostyrko.data.network.dto.CountryDTO
import com.yakogdan.kinopoiskkostyrko.data.network.dto.FilmDTO
import com.yakogdan.kinopoiskkostyrko.data.network.dto.GenreDTO
import com.yakogdan.kinopoiskkostyrko.domain.entity.Country
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.entity.Genre

fun FilmDTO.filmDTOToEntity(): Film = Film(
    kinopoiskId,
    countries?.countriesDTOToEntities() ?: listOf(),
    genres?.genresDTOToEntities() ?: listOf(),
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

fun List<FilmDTO>.filmsDTOToEntities(): List<Film> = map { it.filmDTOToEntity() }

fun CountryDTO.countryDTOToEntity(): Country = Country(country)
fun List<CountryDTO>.countriesDTOToEntities(): List<Country> = map { it.countryDTOToEntity() }

fun GenreDTO.genreDTOToEntity(): Genre = Genre(genre)
fun List<GenreDTO>.genresDTOToEntities(): List<Genre> = map { it.genreDTOToEntity() }