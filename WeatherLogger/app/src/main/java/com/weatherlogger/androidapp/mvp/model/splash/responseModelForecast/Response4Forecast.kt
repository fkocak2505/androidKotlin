package com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Forecast {

    @SerializedName("list")
    @Expose
    val list: MutableList<ForecastList>? = null

    @SerializedName("city")
    @Expose
    val city: WeatherCity? = null

}