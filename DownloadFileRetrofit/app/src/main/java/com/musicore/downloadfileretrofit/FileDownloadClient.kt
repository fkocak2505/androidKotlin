package com.musicore.downloadfileretrofit

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Streaming

interface FileDownloadClient {

    @Streaming
    @GET("gtv-videos-bucket/sample/BigBuckBunny.mp4")
    fun downloadFile(): Call<ResponseBody>

}