package com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Weather
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.WeatherClouds
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.WeatherMain
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.WeatherWind

class ForecastList {

    @SerializedName("dt")
    @Expose
    val dt: Long?= null

    @SerializedName("main")
    @Expose
    val main: WeatherMain?= null

    @SerializedName("weather")
    @Expose
    val weather: MutableList<Weather>?= null

    @SerializedName("clouds")
    @Expose
    val clouds: WeatherClouds?= null

    @SerializedName("wind")
    @Expose
    val wind: WeatherWind?= null

    @SerializedName("dt_txt")
    @Expose
    val dateTxt: String?= null



}