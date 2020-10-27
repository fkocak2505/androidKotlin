package com.weatherlogger.androidapp.mvp.view.fragments.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.weatherlogger.androidapp.R
import com.weatherlogger.androidapp.mvp.model.dataModel.WeatherSummaryModel

class WeatherSummaryRecyclerViewAdapter(
    private val weatherSummaryList: MutableList<WeatherSummaryModel>,
    private val listener: (Int) -> Unit
) :
    RecyclerView.Adapter<WeatherSummaryRecyclerViewAdapter.ModelViewHolder>() {

    //==============================================================================================
    /**
     * ModelViewHolder class for RecyclerView
     */
    //==============================================================================================
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.date)
        private val temp: TextView = view.findViewById(R.id.avarageTemp)
        private val imageOfCityStatus: ImageView = view.findViewById(R.id.imageOfCityStatus)

        //==========================================================================================
        /**
         * Bind Item to RecyclerView item row..
         */
        //==========================================================================================
        @SuppressLint("SetTextI18n")
        fun bindItems(item: WeatherSummaryModel, pos: Int, listener: (Int) -> Unit) {
            date.text = item.time
            temp.text = item.temp + "°C"

            Picasso.get().load(item.icon)
                .into(imageOfCityStatus, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        imageOfCityStatus.setImageResource(R.drawable.ic_launcher_background)
                    }
                })

            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }

    //==============================================================================================
    /**
     * onCreateViewHolder function for RecyclerView..
     */
    //==============================================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_summary_city, parent, false)
        return ModelViewHolder(view)
    }

    //==============================================================================================
    /**
     * Get Item Count from RecyclerView..
     */
    //==============================================================================================
    override fun getItemCount(): Int {
        return weatherSummaryList.size
    }

    //==============================================================================================
    /**
     * onBindViewHolder Function for RecyclerView..
     */
    //==============================================================================================
    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(weatherSummaryList.get(position), position, listener)
    }


}
