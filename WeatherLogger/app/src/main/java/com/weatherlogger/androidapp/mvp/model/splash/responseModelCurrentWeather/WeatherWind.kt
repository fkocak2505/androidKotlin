package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherWind {

    @SerializedName("speed")
    @Expose
    val speed: Float? = null

    @SerializedName("deg")
    @Expose
    val deg: Int? = null

}