package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityCoordinate {

    @SerializedName("lon")
    @Expose
    val lon: Float? = null

    @SerializedName("lat")
    @Expose
    val lat: Float? = null

}