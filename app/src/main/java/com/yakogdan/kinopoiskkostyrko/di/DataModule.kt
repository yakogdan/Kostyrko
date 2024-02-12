package com.yakogdan.kinopoiskkostyrko.di

import android.content.Context
import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteDatabase
import com.yakogdan.kinopoiskkostyrko.data.local.db.FavouriteFilmsDao
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiFactory
import com.yakogdan.kinopoiskkostyrko.data.network.api.ApiService
import com.yakogdan.kinopoiskkostyrko.data.repository.FilmsRepositoryImpl
import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFilmsRepository(impl: FilmsRepositoryImpl): FilmsRepository

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