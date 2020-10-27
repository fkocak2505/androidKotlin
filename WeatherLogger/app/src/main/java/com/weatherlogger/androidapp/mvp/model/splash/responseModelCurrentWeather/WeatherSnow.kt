package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherSnow {

    @SerializedName("1h")
    @Expose
    val oneHour: Float? = null

    @SerializedName("3h")
    @Expose
    val threeHour: Float? = null

}