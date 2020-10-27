package com.weatherlogger.androidapp.mvp.view.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.mvp.model.dataModel.ForecastWeatherModel
import com.weatherlogger.androidapp.mvp.view.fragments.adapter.holder.HolderForecastChild
import com.weatherlogger.androidapp.mvp.view.fragments.adapter.holder.HolderForecastHeader

class DailyWeatherAdapter(
    val context: Context,
    private val items: MutableList<ForecastWeatherModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val FORECAST_HEADER = 0
    private val FOREAST_CHILD = 1

    private lateinit var viewHolder: RecyclerView.ViewHolder

    //==============================================================================================
    /**
     * onCreateViewHolder for RecyclerView
     * This Adapter has 2 layout.
     * FORECAST_HEADER has just date.
     * FORECAST_CHİLD has other weather data..
     */
    //==============================================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            FORECAST_HEADER -> {
                val vEntriesText =
                    inflater.inflate(R.layout.daily_weather_header_layout, parent, false)
                viewHolder = HolderForecastHeader(vEntriesText)
            }
            FOREAST_CHILD -> {
                val vEntriesImage =
                    inflater.inflate(R.layout.daily_weather_item_layout, parent, false)
                viewHolder = HolderForecastChild(vEntriesImage)
            }
        }

        return viewHolder

    }

    //==============================================================================================
    /**
     * get Item Count in RecyclerView..
     */
    //==============================================================================================
    override fun getItemCount(): Int {
        return items.size
    }

    //==============================================================================================
    /**
     * onBindViewHolder for RecyclerView.
     */
    //==============================================================================================
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            FORECAST_HEADER -> {
                val vhEntriesText = holder as HolderForecastHeader
                configureViewHolderForecastHeader(vhEntriesText, position)
            }
            FOREAST_CHILD -> {
                val vhEntriesImage = holder as HolderForecastChild
                configureViewHolderForecastChild(vhEntriesImage, position)
            }
        }
    }

    //==============================================================================================
    /**
     * Get View Type according to listData..
     */
    //==============================================================================================
    override fun getItemViewType(position: Int): Int {
        return when (items[position].mode) {
            "header" -> FORECAST_HEADER
            "child" -> FOREAST_CHILD
            else -> -1
        }

    }

    //==============================================================================================
    /**
     * Go Holder and Bind Items..
     */
    //==============================================================================================
    private fun configureViewHolderForecastHeader(vh1: HolderForecastHeader, position: Int) {

        vh1.bindItems(context, items[position], position)

    }

    //==============================================================================================
    /**
     * go Holder and Bind Items..
     */
    //==============================================================================================
    private fun configureViewHolderForecastChild(vh1: HolderForecastChild, position: Int) {

        vh1.bindItems(context, items[position], position)

    }

}
