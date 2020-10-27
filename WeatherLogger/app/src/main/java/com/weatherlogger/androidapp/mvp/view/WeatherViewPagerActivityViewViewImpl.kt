package com.weatherlogger.androidapp.mvp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.common.WeatherLoggerCommon
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.custom.GradientBackgroundPainter
import com.weatherlogger.androidapp.custom.ZoomOutPageTransformer
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.mvp.model.splash.responseModelForecast.Response4Forecast
import com.weatherlogger.androidapp.mvp.view.fragments.weatherSummary.WeatherSummaryOfCityFragmentViewImpl
import com.weatherlogger.androidapp.mvp.view.fragments.currentWeather.CurrentWeatherFragmentViewImpl
import com.weatherlogger.androidapp.mvp.view.fragments.dailyWeather.DailyWeatherFragmentViewImpl
import com.weatherlogger.androidapp.prefs.StringSharedPrefs
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import com.weatherlogger.androidapp.mvp.view.search.SelectCityActivityViewImpl


class WeatherViewPagerActivityViewViewImpl : AppCompatActivity(), IWeatherViewPagerActivityView {

    private lateinit var response4WeatherData: Response4WeatherData
    private lateinit var response4Forecast: Response4Forecast
    private lateinit var sharedPreferences: SharedPreferences
    private var gradientBackgroundPainter: GradientBackgroundPainter? = null

    //==============================================================================================
    /**
     * WeatherActivityViewPager OnCreate Function
     */
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivityBackgroundColor()

    }

    //==============================================================================================
    /**
     * Init Background Color like Instagram. Background color always change runtime lively..
     */
    //==============================================================================================
    override fun initActivityBackgroundColor() {

        sharedPreferences = getSharedPreferences(
            WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )!!

        getWeatherData()

        prepareToolBar()

        saveData2LocalStorage()

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
                sharedPreferences,
                WeatherLoggerConstant.CURRENT_WEATHER
            )
        ) {
            response4WeatherData = Response4WeatherData()
            response4WeatherData = Gson().fromJson(
                WeatherLoggerSingletonPattern.instance.getResponse4WeatherData(),
                Response4WeatherData::class.java
            )

            response4Forecast = Response4Forecast()
            response4Forecast = Gson().fromJson(
                WeatherLoggerSingletonPattern.instance.getResponse4Forecast(),
                Response4Forecast::class.java
            )

        } else {
            response4WeatherData = Gson().fromJson(
                StringSharedPrefs(
                    sharedPreferences,
                    WeatherLoggerConstant.CURRENT_WEATHER,
                    ""
                ).getValue<String>(), Response4WeatherData::class.java
            )

            response4Forecast = Gson().fromJson(
                StringSharedPrefs(
                    sharedPreferences,
                    WeatherLoggerConstant.CURRENT_WEATHER,
                    ""
                ).getValue<String>(), Response4Forecast::class.java
            )
        }
    }

    //==============================================================================================
    /**
     * Prepare Toolbar config..
     */
    //==============================================================================================
    private fun prepareToolBar() {
        val drawables = IntArray(3)
        drawables[0] = R.drawable.gradient_1
        drawables[1] = R.drawable.gradient_2
        drawables[2] = R.drawable.gradient_3

        gradientBackgroundPainter = GradientBackgroundPainter(rootLayout, drawables)
        gradientBackgroundPainter!!.start()

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_add_city)
        }

        setToolBarText()
        setupViewPager()
    }

    //==============================================================================================
    /**
     * User click save icon and running click listener. Weather data save to LocalStorage..
     */
    //==============================================================================================
    private fun saveData2LocalStorage() {
        saveWeatherData.setOnClickListener {
            StringSharedPrefs(
                sharedPreferences,
                WeatherLoggerConstant.CURRENT_WEATHER,
                ""
            ).setValue(
                Gson().toJson(
                    response4WeatherData
                )
            )

            StringSharedPrefs(
                sharedPreferences,
                WeatherLoggerConstant.FORECAST_WEATHER,
                ""
            ).setValue(
                Gson().toJson(
                    response4Forecast
                )
            )

            Toast.makeText(
                applicationContext,
                "Weather data saved Local Storage SharedPreferences..",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //==============================================================================================
    /**
     * Set toolbar Text...
     */
    //==============================================================================================
    private fun setToolBarText() {
        toolbar_title.text = response4WeatherData.name
    }

    private fun setupViewPager() {
        view_pager.run {
            val fragments: List<Fragment> = listOf(
                CurrentWeatherFragmentViewImpl(),
                DailyWeatherFragmentViewImpl(),
                WeatherSummaryOfCityFragmentViewImpl()
            )
            adapter = SectionsPagerAdapter(
                supportFragmentManager,
                fragments
            )
            offscreenPageLimit = fragments.size

            setPageTransformer(true, ZoomOutPageTransformer())

            dots_indicator.setViewPager(view_pager)
            dots_indicator.setDotsClickable(true)
        }
    }

    //==============================================================================================
    /**
     * SectionPagerAdapter for dot indicators..
     */
    //==============================================================================================
    private class SectionsPagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int) = fragments[position]
        override fun getCount() = fragments.size
    }

    //==============================================================================================
    /**
     * onDestroy Method..
     */
    //==============================================================================================
    override fun onDestroy() {
        super.onDestroy()
        gradientBackgroundPainter!!.stop()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId === android.R.id.home) {
            startActivity(Intent(applicationContext, SelectCityActivityViewImpl::class.java))
        }
        return super.onOptionsItemSelected(menuItem)
    }
}
