package com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.CityCoordinate

class WeatherCity {

    @SerializedName("id")
    @Expose
    val id: Long? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("coord")
    @Expose
    val coord: CityCoordinate? = null

    @SerializedName("sunrise")
    @Expose
    val sunrise: Long? = null

    @SerializedName("sunset")
    @Expose
    val sunset: Long? = null

}