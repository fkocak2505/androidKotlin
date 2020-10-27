package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherSYS {

    @SerializedName("sunrise")
    @Expose
    val lon: Long? = null

    @SerializedName("sunset")
    @Expose
    val lat: Long? = null

}