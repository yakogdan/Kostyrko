package com.yakogdan.kinopoiskkostyrko.data.repository

import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteFilmsDao
import com.yakogdan.kinopoiskkostyrko.data.mapper.filmToDbModel
import com.yakogdan.kinopoiskkostyrko.data.mapper.filmsDbToEntities
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteFilmsDao: FavouriteFilmsDao
) : FavouriteRepository {

    override val favouriteFilms: Flow<List<Film>> = favouriteFilmsDao.getFavouriteCities()
        .map { it.filmsDbToEntities() }

    override fun observeIsFavourite(filmId: Int): Flow<Boolean> =
        favouriteFilmsDao.observeIsFavourite(filmId)

    override suspend fun addToFavourite(film: Film) {
        favouriteFilmsDao.addToFavourite(film.filmToDbModel())
    }

    override suspend fun removeFromFavourite(filmId: Int) {
        favouriteFilmsDao.removeFromFavourite(filmId)
    }
}