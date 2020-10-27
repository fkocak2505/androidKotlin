package com.weatherlogger.androidapp.mvp.view.fragments.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.mvp.model.dataModel.ForecastWeatherModel

class HolderForecastHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textViewDate: TextView? =
        itemView.findViewById(R.id.textViewDate) as TextView

    //==============================================================================================
    /**
     * Bind Forecast Date Data..
     */
    //==============================================================================================
    fun bindItems(
        context: Context,
        item: ForecastWeatherModel,
        pos: Int
    ) {

        textViewDate?.text = item.time

    }
}