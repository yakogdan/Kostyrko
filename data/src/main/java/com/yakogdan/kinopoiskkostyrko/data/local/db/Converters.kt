package com.yakogdan.kinopoiskkostyrko.data.local.db

import androidx.room.TypeConverter
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.CountryDb
import com.yakogdan.kinopoiskkostyrko.data.local.dbmodel.GenreDb

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromCountryList(countryList: List<CountryDb>): String {
        return countryList.joinToString(separator = ";") { it.country }
    }

    @TypeConverter
    @JvmStatic
    fun toCountryList(countryString: String): List<CountryDb> {
        return countryString.split(";").map { CountryDb(it) }
    }

    @TypeConverter
    @JvmStatic
    fun fromGenreList(genreList: List<GenreDb>): String {
        return genreList.joinToString(separator = ";") { it.genre }
    }

    @TypeConverter
    @JvmStatic
    fun toGenreList(genreString: String): List<GenreDb> {
        return genreString.split(";").map { GenreDb(it) }
    }
}
