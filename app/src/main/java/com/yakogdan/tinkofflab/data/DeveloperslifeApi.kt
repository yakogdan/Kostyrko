package com.yakogdan.tinkofflab.data

import com.yakogdan.tinkofflab.data.model.DataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DeveloperslifeApi {

    @GET("random?json=true")
    fun getData(): Single<DataResponse>
}