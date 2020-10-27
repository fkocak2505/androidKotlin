package com.weatherlogger.androidapp.mvp.model.splash

import com.weatherlogger.androidapp.IRequestListener
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast

interface ISplashActivityViewModel {

    fun initApiService()

    fun getCurrentWeather(id: Long, apiKey: String, units: String, iRequestListener: IRequestListener<Response4WeatherData>)

    fun get5DaysWeather(id: Long, apiKey: String, units: String, iRequestListener: IRequestListener<Response4Forecast>)
}