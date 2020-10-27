package com.weatherlogger.androidapp.mvp.model.splash

import com.weatherlogger.androidapp.IRequestListener
import com.weatherlogger.androidapp.apiUtil.ApiUtils
import com.weatherlogger.androidapp.apiUtil.interfaced.IApiService
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import retrofit2.Call
import retrofit2.Response

/**
 *
 * This is Presenter Layer. This class just take a parameter and send to Model layer..
 *
 */
class SplashActivityViewModelImpl : ISplashActivityViewModel {

    private lateinit var iApiService: IApiService

    //==============================================================================================
    /**
     * init API Service. This Method declare endpoint to RetrofitClient..
     */
    //==============================================================================================
    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    //==============================================================================================
    /**
     * Send a request to currentWeather data to OpenWeatherAPI...
     */
    //==============================================================================================
    override fun getCurrentWeather(
        id: Long,
        apiKey: String, units: String,
        iRequestListener: IRequestListener<Response4WeatherData>
    ) {

        initApiService()

        iApiService.getWeatherData(id, apiKey, units)
            .enqueue(object : retrofit2.Callback<Response4WeatherData> {
                override fun onFailure(call: Call<Response4WeatherData>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4WeatherData>?,
                    response: Response<Response4WeatherData>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })


    }

    //==============================================================================================
    /**
     * Send a requet to foreacast data to OpenWeatherAPI...
     */
    //==============================================================================================
    override fun get5DaysWeather(
        id: Long,
        apiKey: String, units: String,
        iRequestListener: IRequestListener<Response4Forecast>
    ) {
        initApiService()

        iApiService.getWeatherForecest(id, apiKey, units)
            .enqueue(object : retrofit2.Callback<Response4Forecast> {
                override fun onFailure(call: Call<Response4Forecast>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4Forecast>?,
                    response: Response<Response4Forecast>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }
}