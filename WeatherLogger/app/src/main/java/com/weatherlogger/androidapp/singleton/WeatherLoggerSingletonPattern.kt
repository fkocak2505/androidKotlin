package com.weatherlogger.androidapp.singleton

class WeatherLoggerSingletonPattern {

    companion object {
        val instance: WeatherLoggerSingletonPattern by lazy { WeatherLoggerSingletonPattern() }
    }

    //==============================================================================================
    /**
     * Response4Forecast
     */
    //==============================================================================================
    private var response4Forecast: String? = null

    fun getResponse4Forecast(): String {
        return response4Forecast!!
    }

    fun setResponse4Forecast(valOfResponse4Forecast: String) {
        response4Forecast = valOfResponse4Forecast
    }

    //==============================================================================================
    /**
     * Response4WeatherData
     */
    //==============================================================================================
    private var response4WeatherData: String? = null

    fun getResponse4WeatherData(): String {
        return response4WeatherData!!
    }

    fun setResponse4WeatherData(valOfResponse4WeatherData: String) {
        response4WeatherData = valOfResponse4WeatherData
    }

    //==============================================================================================
    /**
     * City Id
     */
    //==============================================================================================
    private var cityId: Long? = 0

    fun getCityId(): Long? {
        return cityId!!
    }

    fun setCityId(valOfCityId: Long?) {
        cityId = valOfCityId
    }

}