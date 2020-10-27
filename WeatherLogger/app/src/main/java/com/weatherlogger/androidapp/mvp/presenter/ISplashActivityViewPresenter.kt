package com.weatherlogger.androidapp.mvp.presenter

interface ISplashActivityViewPresenter {

    fun getCurrentWeather(id: Long, apiKey: String, units: String)

    fun get5DaysWeather(id: Long, apiKey: String, units: String)

}