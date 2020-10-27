package com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherMain {

    @SerializedName("temp")
    @Expose
    val temp: Float? = null

    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null

    @SerializedName("temp_min")
    @Expose
    val temp_min: Float? = null

    @SerializedName("temp_max")
    @Expose
    val temp_max: Float? = null

    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float? = null

    @SerializedName("sea_level")
    @Expose
    val seaLevel: Int? = null

    @SerializedName("grnd_level")
    @Expose
    val grndLevel: Int? = null


}