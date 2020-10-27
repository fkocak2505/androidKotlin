package com.musicore.musicoreandroid.model


import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PlayListApisService {

    private val BASE_URL = "http://api.napster.com"
    private val BASE_URL_TRENDYOL = "https://api.trendyol.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL_TRENDYOL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(PlayListApi::class.java)

    /*fun getPlayList(): Single<Response4PLayList> {
        return api.getPlayList()
    }*/

    fun getTrendyolData(): Single<Response4TrendyolData> {
        return api.getTrendyolData("application/json","1","interview")
    }

}