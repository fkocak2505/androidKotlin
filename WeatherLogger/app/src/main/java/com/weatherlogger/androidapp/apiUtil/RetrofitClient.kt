package com.weatherlogger.androidapp.apiUtil

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    //==============================================================================================
    /**
     * Companion Object to create RetrofitClient
     * ReadTimeout is 10 second
     * WriteTimeout is 10 second
     */
    //==============================================================================================
    companion object {
        fun getClient(baseURL: String): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }

}