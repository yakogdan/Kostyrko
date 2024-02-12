package com.yakogdan.kinopoiskkostyrko.data.repository

import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteFilmsDao
import com.yakogdan.kinopoiskkostyrko.data.mapper.filmToDbModel
import com.yakogdan.kinopoiskkostyrko.data.mapper.filmsDTOToEntities
import com.yakogdan.kinopoiskkostyrko.data.mapper.filmsDbToEntities
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiService
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val favouriteFilmsDao: FavouriteFilmsDao,
    private val apiService: ApiService
) : FilmsRepository {

    override val favouriteFilms: Flow<List<Film>> = favouriteFilmsDao.getFavouriteCities()
        .map { it.filmsDbToEntities() }

    override fun observeIsFavourite(filmId: Int): Flow<Boolean> =
        favouriteFilmsDao.observeIsFavourite(filmId)

    val filmsIsFavourite: StateFlow<Boolean> = MutableStateFlow(false)

    override suspend fun addToFavourite(film: Film) {
        favouriteFilmsDao.addToFavourite(film.filmToDbModel())
    }

    override suspend fun removeFromFavourite(filmId: Int) {
        favouriteFilmsDao.removeFromFavourite(filmId)
    }

    override suspend fun getPopularFilms(): List<Film> =
        apiService.loadTopFilms().films.filmsDTOToEntities()

    override suspend fun changeToFavourite() {

    }

    override suspend fun changeToPopular() {

    }
}