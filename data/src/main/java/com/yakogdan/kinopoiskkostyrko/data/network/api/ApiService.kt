package com.yakogdan.kinopoiskkostyrko.data.network.api

import com.yakogdan.kinopoiskkostyrko.data.network.dto.TopFilms
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("X-API-KEY: $API_KEY_MY")
    @GET("/api/v2.2/films/collections?type=TOP_POPULAR_ALL&page=1")
    suspend fun loadTopFilms(): TopFilms

    companion object {
        private const val API_KEY_MY = "7560e292-123f-4ea3-98a5-f42d521bad24"
        private const val API_KEY_TK = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }
}