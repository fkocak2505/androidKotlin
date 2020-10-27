package com.weatherlogger.androidapp.mvp.view.fragments.weatherSummary

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.common.WeatherLoggerCommon
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.mvp.model.dataModel.WeatherSummaryModel
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.view.fragments.adapter.WeatherSummaryRecyclerViewAdapter
import com.weatherlogger.androidapp.prefs.StringSharedPrefs
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern
import kotlinx.android.synthetic.main.fragment_weather_summary.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*


class WeatherSummaryOfCityFragmentViewImpl : Fragment(),
    IWeatherSummaryOfCityFragmentView {

    private lateinit var viewP: View

    private lateinit var response4WeatherData: Response4WeatherData
    private lateinit var sharedPrefs: SharedPreferences


    //==============================================================================================
    /**
     * WeatherSummary onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_weather_summary, container, false)

        initWeatherSummaryOfCityFragmentView()

        return viewP
    }

    //==============================================================================================
    /**
     * Init Weather Summary Fragment Component..
     */
    //==============================================================================================
    override fun initWeatherSummaryOfCityFragmentView() {
        sharedPrefs = activity?.getSharedPreferences(
            WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )!!

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
    private fun getWeatherData() {
        if (!WeatherLoggerCommon.isSavedLocal(
                sharedPrefs,
                WeatherLoggerConstant.CURRENT_WEATHER
            )
        ) {
            response4WeatherData = Response4WeatherData()
            response4WeatherData = Gson().fromJson(
                WeatherLoggerSingletonPattern.instance.getResponse4WeatherData(),
                Response4WeatherData::class.java
            )
        } else
            response4WeatherData = Gson().fromJson(
                StringSharedPrefs(
                    sharedPrefs,
                    WeatherLoggerConstant.CURRENT_WEATHER,
                    ""
                ).getValue<String>(), Response4WeatherData::class.java
            )

    }

    //==============================================================================================
    /**
     * Set RecyclerView data config for Weather Summary..
     */
    //==============================================================================================
    private fun setRecyclerViewData() {
        viewP.recyclerViewWeatherSummary.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        viewP.recyclerViewWeatherSummary.adapter =
            WeatherSummaryRecyclerViewAdapter(getSummaryWeatherData(response4WeatherData)) {

            }
    }

    //==============================================================================================
    /**
     * Prepare Weather Summary Data for RecyclerView..
     */
    //==============================================================================================
    private fun getSummaryWeatherData(response4WeatherData: Response4WeatherData): MutableList<WeatherSummaryModel> {
        val arrOfWeatherSummaryOfCity: MutableList<WeatherSummaryModel> = mutableListOf()

        val icon =
            "http://openweathermap.org/img/w/${response4WeatherData.weather?.get(0)!!.icon}.png"
        val dateTimeAsString = DateTime(response4WeatherData.dt?.times(1000), DateTimeZone.UTC).toString()
        val time = WeatherLoggerCommon.getUTCDate2Date(dateTimeAsString)

        arrOfWeatherSummaryOfCity.add(
            WeatherSummaryModel(
                response4WeatherData.name,
                icon,
                response4WeatherData.weather[0].main,
                response4WeatherData.main?.temp.toString(),
                time
            )
        )

        return arrOfWeatherSummaryOfCity

    }
}