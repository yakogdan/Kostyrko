package com.yakogdan.kostyrkoTinkoff.data

import com.yakogdan.kostyrkoTinkoff.data.model.DataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DeveloperslifeApi {

    @GET("random?json=true")
    fun getData(): Single<DataResponse>
}