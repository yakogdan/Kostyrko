package com.yakogdan.kinopoiskkostyrko.di

import android.content.Context
import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteDatabase
import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteFilmsDao
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiFactory
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiService
import com.yakogdan.kinopoiskkostyrko.data.repository.FavouriteRepositoryImpl
import com.yakogdan.kinopoiskkostyrko.data.repository.PopularRepositoryImpl
import com.yakogdan.kinopoiskkostyrko.domain.repository.FavouriteRepository
import com.yakogdan.kinopoiskkostyrko.domain.repository.PopularRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @[ApplicationScope Binds]
    fun bindPopularRepository(impl: PopularRepositoryImpl): PopularRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase =
            FavouriteDatabase.getInstance(context = context)

        @[ApplicationScope Provides]
        fun provideFavouriteFilmsDao(database: FavouriteDatabase): FavouriteFilmsDao =
            database.favouriteFilmsDao()
    }
}