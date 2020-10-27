package com.weatherlogger.androidapp.mvp.view.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.mvp.model.dataModel.CityModel
import com.weatherlogger.androidapp.mvp.view.fragments.adapter.SelectCityRecyclerViewAdapter
import com.weatherlogger.androidapp.mvp.view.splash.SplashActivityViewImpl
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern
import kotlinx.android.synthetic.main.search_city.*

class SelectCityActivityViewImpl : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_city)

        sharedPrefs = getSharedPreferences(WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME, Context.MODE_PRIVATE)

        recyclerViewSearchCity.setHasFixedSize(true)
        recyclerViewSearchCity.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerViewSearchCity.adapter =
            SelectCityRecyclerViewAdapter(getCityData()) {
                WeatherLoggerSingletonPattern.instance.setCityId(it.cityId)
                sharedPrefs.edit().remove(WeatherLoggerConstant.CURRENT_WEATHER)
                sharedPrefs.edit().remove(WeatherLoggerConstant.FORECAST_WEATHER)
                sharedPrefs.edit().commit()
                WeatherLoggerSingletonPattern.instance.setResponse4Forecast("")
                WeatherLoggerSingletonPattern.instance.setResponse4WeatherData("")

                startActivity(Intent(applicationContext, SplashActivityViewImpl::class.java))
            }
    }

    private fun getCityData(): MutableList<CityModel> {
        var arrOfCity: MutableList<CityModel> = mutableListOf()

        arrOfCity.add(CityModel(456172, "Riga"))
        arrOfCity.add(CityModel(323784, "Ankara"))
        arrOfCity.add(CityModel(323776, "Antalya"))
        arrOfCity.add(CityModel(745042, "İstanbul"))

        return arrOfCity
    }
}