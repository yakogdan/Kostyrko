package com.yakogdan.kinopoiskkostyrko.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.FilmDb
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteFilmsDao {

    @Query("SELECT * FROM favourite_films")
    fun getFavouriteCities(): Flow<List<FilmDb>>

    @Query("SELECT EXISTS(SELECT * FROM favourite_films WHERE kinopoiskId=:filmId LIMIT 1)")
    fun observeIsFavourite(filmId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(filmDb: FilmDb)

    @Query("DELETE FROM favourite_films WHERE kinopoiskId=:filmId")
    suspend fun removeFromFavourite(filmId: Int)
}