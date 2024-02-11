package com.yakogdan.kinopoiskkostyrko.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.FilmDb

@Database(entities = [FilmDb::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun favouriteFilmsDao(): FavouriteFilmsDao

    companion object {

        private const val DB_NAME = "FavouriteDatabase"
        private var INSTANCE: FavouriteDatabase? = null
        private var LOCK = Any()

        fun getInstance(context: Context): FavouriteDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val database = Room.databaseBuilder(
                    context = context,
                    klass = FavouriteDatabase::class.java,
                    name = DB_NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}