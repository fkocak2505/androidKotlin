package com.musicore.musicoreandroid.model


import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlayListApi {

    /*@GET("v2.2/playlists?apikey=YTkxZTRhNzAtODdlNy00ZjMzLTg0MWItOTc0NmZmNjU4Yzk4")
    fun getPlayList(): Single<Response4PLayList>*/

    @GET("/zeus/widget/display")
    fun getTrendyolData(
        @Header("Content-Type") contentType: String,
        @Header("Build") build: String,
        @Query("widgetPageName") widgetPageName: String
    ): Single<Response4TrendyolData>


}