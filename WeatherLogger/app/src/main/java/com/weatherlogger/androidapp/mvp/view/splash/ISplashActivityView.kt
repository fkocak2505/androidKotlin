package com.weatherlogger.androidapp.mvp.view.splash

import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast

interface ISplashActivityView {

    fun initSplashActivityPresenter()

    fun getCurrentWeather(id: Long, apiKey: String, units: String)

    fun get5DaysWeather(id: Long, apiKey: String, units: String)

    fun handleCurrentWeatherData(response4WeatherData: Response4WeatherData)

    fun handleForecastData(response4Forecast: Response4Forecast)

}