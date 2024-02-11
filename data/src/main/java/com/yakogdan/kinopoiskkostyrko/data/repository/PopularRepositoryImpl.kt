package com.yakogdan.kinopoiskkostyrko.data.repository

import com.yakogdan.kinopoiskkostyrko.data.mapper.filmsDTOToEntities
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiService
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PopularRepository {
    override suspend fun getPopularFilms(): List<Film> =
        apiService.loadTopFilms().films.filmsDTOToEntities()
}