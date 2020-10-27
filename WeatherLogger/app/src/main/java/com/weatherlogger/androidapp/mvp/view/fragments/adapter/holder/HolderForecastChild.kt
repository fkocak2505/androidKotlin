package com.weatherlogger.androidapp.mvp.view.fragments.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.mvp.model.dataModel.ForecastWeatherModel

class HolderForecastChild(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textWeather: TextView? =
        itemView.findViewById(R.id.text_weather) as TextView
    private var imageStatusWeather: ImageView? =
        itemView.findViewById(R.id.image_icon_city_item) as ImageView
    private var textViewDataTime: TextView? =
        itemView.findViewById(R.id.textViewDataTime) as TextView

    private var maxTemp: TextView? =
        itemView.findViewById(R.id.text_temp_max) as TextView

    private var minTemp: TextView? =
        itemView.findViewById(R.id.text_temp_min) as TextView

    //==============================================================================================
    /**
     * Bind Forecast Child Data...
     */
    //==============================================================================================
    fun bindItems(
        context: Context,
        item: ForecastWeatherModel,
        pos: Int
    ) {

        textWeather?.text = item.status
        textViewDataTime?.text = item.time
        maxTemp?.text = item.maxTemp + "°C"
        minTemp?.text = item.minTemp + "°C"

        Picasso.get().load(item.weatherIcon)
            .into(imageStatusWeather, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {
                    imageStatusWeather?.setImageResource(R.drawable.ic_launcher_background)
                }
            })

    }

}