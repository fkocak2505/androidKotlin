package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4WeatherData {

    @SerializedName("coord")
    @Expose
    val coord: CityCoordinate? = null

    @SerializedName("weather")
    @Expose
    val weather: MutableList<Weather>? = null

    @SerializedName("main")
    @Expose
    val main: WeatherMain? = null

    @SerializedName("wind")
    @Expose
    val wind: WeatherWind? = null

    @SerializedName("rain")
    @Expose
    val rain: WeatherRain? = null

    @SerializedName("snow")
    @Expose
    val snow: WeatherSnow? = null

    @SerializedName("clouds")
    @Expose
    val clouds: WeatherClouds? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("dt")
    @Expose
    val dt: Long? = null

    @SerializedName("visibility")
    @Expose
    val visibility: Long? = null

}