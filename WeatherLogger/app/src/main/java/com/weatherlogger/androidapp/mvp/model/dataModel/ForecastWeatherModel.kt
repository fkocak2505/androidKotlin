package com.weatherlogger.androidapp.mvp.model.dataModel

data class ForecastWeatherModel(val mode: String, val weatherIcon: String?, val status: String?, val minTemp: String?, val time: String, val maxTemp: String? )