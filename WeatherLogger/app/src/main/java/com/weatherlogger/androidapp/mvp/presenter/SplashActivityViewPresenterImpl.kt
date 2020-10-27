package com.weatherlogger.androidapp.mvp.presenter

import com.weatherlogger.androidapp.IRequestListener
import com.weatherlogger.androidapp.mvp.model.splash.ISplashActivityViewModel
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import com.weatherlogger.androidapp.mvp.view.splash.ISplashActivityView
import retrofit2.Response

/**
 *
 * This is Presenter Layer. This class just take a parameter and send to Model layer..
 *
 */
class SplashActivityViewPresenterImpl(
    private val iSplashActivityViewModel: ISplashActivityViewModel,
    val iSplashActivityView: ISplashActivityView): ISplashActivityViewPresenter {

    //==============================================================================================
    /**
     * Presenter of getCurrentWeather function..
     * This function trigger Model layers getCurrentWeather function with parameter..
     */
    //==============================================================================================
    override fun getCurrentWeather(id: Long, apiKey: String, units: String) {
        iSplashActivityViewModel.getCurrentWeather(id, apiKey, units, object : IRequestListener<Response4WeatherData>{
            override fun onSuccess(response: Response<Response4WeatherData>) {
                iSplashActivityView.handleCurrentWeatherData(response.body() as Response4WeatherData)
            }

            override fun onUnSuccess(response: Response<Response4WeatherData>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })

    }

    //==============================================================================================
    /**
     * Presenter of get5DaysForecastWeather function..
     * This function trigger Model layers get5DaysWeather function with parameter..
     */
    //==============================================================================================
    override fun get5DaysWeather(id: Long, apiKey: String, units: String) {
        iSplashActivityViewModel.get5DaysWeather(id, apiKey, units, object : IRequestListener<Response4Forecast>{
            override fun onSuccess(response: Response<Response4Forecast>) {
                iSplashActivityView.handleForecastData(response.body() as Response4Forecast)
            }

            override fun onUnSuccess(response: Response<Response4Forecast>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }
}