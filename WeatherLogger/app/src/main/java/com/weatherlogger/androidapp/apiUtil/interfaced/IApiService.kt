package com.weatherlogger.androidapp.apiUtil.interfaced

import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import retrofit2.Call
import retrofit2.http.*

//==============================================================================================
/**
 * All request method interface in here...
 */
//==============================================================================================

interface IApiService {

    @GET("data/2.5/weather")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getWeatherData(@Query("id") id: Long, @Query("appid") perPage: String, @Query("units") units: String): Call<Response4WeatherData>

    @GET("data/2.5/forecast")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getWeatherForecest(@Query("id") id: Long, @Query("appid") perPage: String, @Query("units") units: String): Call<Response4Forecast>

}