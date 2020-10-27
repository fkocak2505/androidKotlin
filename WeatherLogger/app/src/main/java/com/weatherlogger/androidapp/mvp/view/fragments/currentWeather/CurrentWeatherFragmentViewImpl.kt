package com.weatherlogger.androidapp.mvp.view.fragments.currentWeather

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.common.WeatherLoggerCommon
import com.weatherlogger.androidapp.constant.WeatherLoggerConstant
import com.weatherlogger.androidapp.custom.WindDirection
import com.weatherlogger.androidapp.mvp.model.splash.responseModelCurrentWeather.Response4WeatherData
import com.weatherlogger.androidapp.prefs.StringSharedPrefs
import com.weatherlogger.androidapp.singleton.WeatherLoggerSingletonPattern
import kotlinx.android.synthetic.main.current_weather.*
import kotlinx.android.synthetic.main.current_weather.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


class CurrentWeatherFragmentViewImpl : Fragment(),
    ICurrentWeatherFragmentView,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewP: View
    private lateinit var response4WeatherData: Response4WeatherData
    private lateinit var sharedPreferences: SharedPreferences

    //==============================================================================================
    /**
     * CurrentWeatherFragmentView onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.current_weather, container, false)

        initCurrentWeatherFragment()

        return viewP
    }

    //==============================================================================================
    /**
     * Init Current Weather Fragment Component..
     */
    //==============================================================================================
    override fun initCurrentWeatherFragment() {
        sharedPreferences =
            activity?.getSharedPreferences(
                WeatherLoggerConstant.WEATHER_SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )!!

        viewP.swipe_refresh_layout.setOnRefreshListener(this)

        getWeatherData()

        setCurrentData()

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
                sharedPreferences,
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
                    sharedPreferences,
                    WeatherLoggerConstant.CURRENT_WEATHER,
                    ""
                ).getValue<String>(), Response4WeatherData::class.java
            )
    }

    //==============================================================================================
    /**
     * Set Current Weather Root Function..
     */
    //==============================================================================================
    private fun setCurrentData() {
        setPresentWeatherIcon(response4WeatherData.weather?.get(0)?.icon!!)
        setCurrentWeatherDegree(response4WeatherData.main?.temp!!)
        setMainWeatherInfo(response4WeatherData.weather?.get(0)!!.main!!)
        setLastUpdateInfo(response4WeatherData.dt!!)

        setCardView1Data(response4WeatherData)
        setCardVeiw2Data(response4WeatherData)

    }

    //==============================================================================================
    /**
     * Set present weather icon for this city..
     */
    //==============================================================================================
    private fun setPresentWeatherIcon(icon: String) {

        Picasso.get().load("http://openweathermap.org/img/w/$icon.png")
            .into(viewP.image_icon, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {
                    image_icon.setImageResource(R.drawable.ic_launcher_background)
                }
            })
    }

    //==============================================================================================
    /**
     * Set Current Weather Degree bu Celcius..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setCurrentWeatherDegree(temp: Float){
        viewP.text_temperature.text = "$temp°C"
    }

    //==============================================================================================
    /**
     * Set Current Weather Description Data..
     */
    //==============================================================================================
    private fun setMainWeatherInfo(mainWeather: String){
        viewP.text_main_weather.text = mainWeather
    }

    //==============================================================================================
    /**
     * Set Last Updated Date for weather..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setLastUpdateInfo(date: Long){
        val dateTimeAsString = DateTime(date * 1000, DateTimeZone.UTC).toString()
        val date = WeatherLoggerCommon.getUTCDate2Date(dateTimeAsString)
        viewP.text_last_update.text = "Last Updated: $date"
    }

    //==============================================================================================
    /**
     * Set CardView Data for weather. Example: Wind, Rain, Pressure etc.
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setCardView1Data(response4WeatherData: Response4WeatherData){
        viewP.card_view1.visibility = View.VISIBLE
        viewP.text_pressure.text = response4WeatherData.main?.pressure.toString() + " hPa"
        viewP.text_humidity.text = response4WeatherData.main?.humidity.toString() + " %"

        response4WeatherData.rain?.let {
            viewP.text_rain.text = response4WeatherData.rain.oneHour.toString() + " mm"
        } ?: run{
            viewP.text_rain.text = "--"
        }

        response4WeatherData.visibility?.let {
            viewP.text_visibility.text = response4WeatherData.visibility.toString() + " km"
        } ?: run{
            viewP.text_visibility.text = "--"
        }

    }

    //==============================================================================================
    /**
     * Set CardView Data for weather. Play Animation for wind speed. If Wind speed change, Than
     * Animation speed is change..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setCardVeiw2Data(response4WeatherData: Response4WeatherData){
        viewP.card_view2.visibility = View.VISIBLE
        viewP.windmill1.winSpeed = response4WeatherData.wind?.speed?.toDouble()!!
        viewP.windmill2.winSpeed = response4WeatherData.wind.speed.toDouble()
        viewP.text_wind_dir.text = "Direction: " + WindDirection.fromDegrees(response4WeatherData.wind.deg?.toDouble()!!).toString()
        viewP.text_wind_speed.text = "Speed: " + response4WeatherData.wind.speed.toString() + " m/s"
    }

    //==============================================================================================
    /**
     * onSwipe Refresh Feature
     */
    //==============================================================================================
    override fun onRefresh() {
        swipe_refresh_layout.isRefreshing = false
    }


}