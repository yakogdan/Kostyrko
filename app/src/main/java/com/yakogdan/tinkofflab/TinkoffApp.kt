package com.yakogdan.tinkofflab

import android.app.Application
import com.yakogdan.tinkofflab.data.DeveloperslifeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TinkoffApp: Application() {

    private val baseUrl = "https://developerslife.ru/"
    val developerslifeApi: DeveloperslifeApi by lazy { configureRetrofit() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private fun configureRetrofit(): DeveloperslifeApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DeveloperslifeApi::class.java)
    }

    companion object {
        lateinit var instance: TinkoffApp
    }
}