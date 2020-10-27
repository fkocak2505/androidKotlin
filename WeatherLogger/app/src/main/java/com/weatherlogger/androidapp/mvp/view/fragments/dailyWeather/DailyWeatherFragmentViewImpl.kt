package com.weatherlogger.androidapp.mvp.view.fragments.dailyWeather

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.common.WeatherLoggerCommon
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.mvp.model.dataModel.ForecastWeatherModel
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import com.weatherlogger.androidapp.mvp.view.fragments.adapter.DailyWeatherAdapter
import com.weatherlogger.androidapp.prefs.StringSharedPrefs
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern
import kotlinx.android.synthetic.main.daily_weather.view.*


class DailyWeatherFragmentViewImpl : Fragment(),
    IDailyWeatherFragmentView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewP: View

    private lateinit var response4Forecast: Response4Forecast
    private lateinit var sharedPrefs: SharedPreferences

    //==============================================================================================
    /**
     * DailyWeather onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.daily_weather, container, false)

        initDailtWeatherFragment()

        return viewP
    }

    //==============================================================================================
    /**
     * Init Daily Weather Fragment Component..
     */
    //==============================================================================================
    override fun initDailtWeatherFragment() {
        sharedPrefs = activity?.getSharedPreferences(
            WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )!!

        viewP.swipe_refresh_layout.setOnRefreshListener(this)

        getWeatherData()

        setRecyclerViewData()

    }

    //==============================================================================================
    /**
     * Get Weather Data. If user save data to LocalStorage, Weather data come storage..
     * If user doesn't save data to LocalStorage. Weather data come API and this data stored
     * singleton pattern instance...
     */
    //==============================================================================================
    private fun getWeatherData(){
        if (!WeatherLoggerCommon.isSavedLocal(
                sharedPrefs,
                WeatherLoggerConstant.CURRENT_WEATHER
            )
        ) {
            response4Forecast = Response4Forecast()
            response4Forecast = Gson().fromJson(WeatherLoggerSingletonPattern.instance.getResponse4Forecast(), Response4Forecast::class.java)
        } else
            response4Forecast = Gson().fromJson(
                StringSharedPrefs(
                    sharedPrefs,
                    WeatherLoggerConstant.FORECAST_WEATHER,
                    ""
                ).getValue<String>(), Response4Forecast::class.java
            )
    }

    //==============================================================================================
    /**
     * Set Daily RecyclerView data for 5 Days Forecast..
     */
    //==============================================================================================
    private fun setRecyclerViewData(){
        viewP.recycler_daily_weathers.run {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = DailyWeatherAdapter(
                activity?.applicationContext!!,
                prepareDailyData(response4Forecast)
            )

            DividerItemDecoration(context, linearLayoutManager.orientation)
                .apply {
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.daily_weather_divider
                    )?.let(::setDrawable)
                }
                .let(::addItemDecoration)

        }
    }

    //==============================================================================================
    /**
     * Prepare Daily Data for RecyclerView..
     */
    //==============================================================================================
    private fun prepareDailyData(response4Forecast: Response4Forecast): MutableList<ForecastWeatherModel> {
        val forecastWeatherData: MutableList<ForecastWeatherModel> = mutableListOf()
        val arrOfDayDate : MutableList<String> = mutableListOf()

        var count = 0
        response4Forecast.list?.forEach {
            count++
            val icon = "http://openweathermap.org/img/w/${it.weather?.get(0)!!.icon}.png"
            val time = it.dateTxt?.substring(it.dateTxt.indexOf(" ") + 1)
            val time2 = it.dateTxt?.substring(0 ,it.dateTxt.indexOf(" ") + 1)
            forecastWeatherData.add(
                ForecastWeatherModel(
                    "child",
                    icon,
                    it.weather[0].main!!,
                    it.main?.temp_min.toString(),
                    time!!,
                    it.main?.temp_max.toString()
                )
            )

            if(count % 9 == 0)
                arrOfDayDate.add(time2!!)

        }

        arrOfDayDate.add("")

        for(i in 0..4){
            forecastWeatherData.add((i*9), ForecastWeatherModel("header", null, null ,null, arrOfDayDate[i], null ))
        }

        return forecastWeatherData

    }

    //==============================================================================================
    /**
     * onSwipe Refresh Feature
     */
    //==============================================================================================
    override fun onRefresh() {
        viewP.swipe_refresh_layout.isRefreshing = false
    }

}