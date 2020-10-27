package com.weatherlogger.androidapp.mvp.view.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.common.WeatherLoggerCommon
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.mvp.model.splash.SplashActivityViewModelImpl
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import com.weatherlogger.androidapp.mvp.presenter.SplashActivityViewPresenterImpl
import com.weatherlogger.androidapp.mvp.view.WeatherViewPagerActivityViewViewImpl
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern

class SplashActivityViewImpl : AppCompatActivity(), ISplashActivityView {

    private lateinit var splashActivityViewPresenterImpl: SplashActivityViewPresenterImpl
    private lateinit var sharedPreferences: SharedPreferences

    private var cityId: Long = 456172 // FOR RIGA

    //==============================================================================================
    /**
     * SplashActivityView onCreate Method Function..
     */
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (WeatherLoggerSingletonPattern.instance.getCityId() == 0.toLong())
            WeatherLoggerSingletonPattern.instance.setCityId(cityId)
        else{
            cityId = WeatherLoggerSingletonPattern.instance.getCityId()!!
        }

        initSplashActivityPresenter()

        prepareWeatherData()
    }

    //==============================================================================================
    /**
     * Init SplashActivity Init and prepare SharedPrefs for LocalStorage...
     */
    //==============================================================================================
    override fun initSplashActivityPresenter() {
        splashActivityViewPresenterImpl =
            SplashActivityViewPresenterImpl(SplashActivityViewModelImpl(), this)

        sharedPreferences = getSharedPreferences(
            WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    //==============================================================================================
    /**
     * Prepare Weather Data. If user save LocalStorage, app doesn't request to API.
     */
    //==============================================================================================
    private fun prepareWeatherData() {
        if (!WeatherLoggerCommon.isSavedLocal(
                sharedPreferences,
                WeatherLoggerConstant.CURRENT_WEATHER
            )
        )
            getCurrentWeather(cityId, WeatherLoggerConstant.WEATHER_API_KEY, "metric")
        else
            Handler().postDelayed({
                startActivity(
                    Intent(
                        applicationContext,
                        WeatherViewPagerActivityViewViewImpl::class.java
                    )
                )
                finish()
            }, 2000)
    }

    //==============================================================================================
    /**
     * Get Current Weather and request to Presenter Layer with parameters..
     * Parameters is $id and $apiKey
     * ApiKey generated OpenWeatherAPI..
     */
    //==============================================================================================
    override fun getCurrentWeather(id: Long, apiKey: String, units: String) {
        splashActivityViewPresenterImpl.getCurrentWeather(id, apiKey, units)
    }

    //==============================================================================================
    /**
     * Get 5 Days Forecast Weather Data and request to Presenter Layer with parameters
     * Parameters is $id and $apiKey
     * ApiKey generated OpenWeatherAPI..
     */
    //==============================================================================================
    override fun get5DaysWeather(id: Long, apiKey: String, units: String) {
        splashActivityViewPresenterImpl.get5DaysWeather(id, apiKey, units)
    }

    //==============================================================================================
    /**
     * Handle Response Current Data from API. This data come from Presenter Layer with response
     * object..
     * Response saved Singleton Pattern Instance..
     */
    //==============================================================================================
    override fun handleCurrentWeatherData(response4WeatherData: Response4WeatherData) {

        WeatherLoggerSingletonPattern.instance.setResponse4WeatherData(
            Gson().toJson(
                response4WeatherData
            )
        )

        get5DaysWeather(cityId, WeatherLoggerConstant.WEATHER_API_KEY, "metric")
    }

    //==============================================================================================
    /**
     * Handle Response 5 Day Forecast Data from API. This data come from Presenter Layer with response
     * object
     * Response saved Singleton Pattern Instance..
     */
    //==============================================================================================
    override fun handleForecastData(response4Forecast: Response4Forecast) {

        WeatherLoggerSingletonPattern.instance.setResponse4Forecast(Gson().toJson(response4Forecast))
        startActivity(Intent(applicationContext, WeatherViewPagerActivityViewViewImpl::class.java))
    }
}