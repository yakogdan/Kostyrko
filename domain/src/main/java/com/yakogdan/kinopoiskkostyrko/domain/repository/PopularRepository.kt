package com.yakogdan.kinopoiskkostyrko.domain.repository

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film

interface PopularRepository {

    suspend fun getPopularFilms(): List<Film>
}