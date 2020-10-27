package com.weatherlogger.androidapp.apiUtil

import com.weatherlogger.androidapp.apiUtil.interfaced.IApiService

class ApiUtils {

    //==============================================================================================
    /**
     * Companion Object. For Declare Endpoint to Retrofit2..
     */
    //==============================================================================================
    companion object{
        private val BASE_WEATHER_API: String =
            "https://api.openweathermap.org/"

        fun getAPIService(): IApiService {
            return RetrofitClient.getClient(BASE_WEATHER_API).create(IApiService::class.java)
        }
    }

}