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
import com.weatherlogger.androidapp.mvp.model.dataModel.CityModel
import com.weatherlogger.androidapp.mvp.model.dataModel.WeatherSummaryModel

class SelectCityRecyclerViewAdapter(
    private val cityList: MutableList<CityModel>,
    private val listener: (CityModel) -> Unit
) :
    RecyclerView.Adapter<SelectCityRecyclerViewAdapter.ModelViewHolder>() {

    //==============================================================================================
    /**
     * ModelViewHolder class for RecyclerView
     */
    //==============================================================================================
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cityName: TextView = view.findViewById(R.id.cityName)

        //==========================================================================================
        /**
         * Bind Item to RecyclerView item row..
         */
        //==========================================================================================
        @SuppressLint("SetTextI18n")
        fun bindItems(item: CityModel, pos: Int, listener: (CityModel) -> Unit) {
            cityName.text = item.cityName

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    //==============================================================================================
    /**
     * onCreateViewHolder function for RecyclerView..
     */
    //==============================================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return ModelViewHolder(view)
    }

    //==============================================================================================
    /**
     * Get Item Count from RecyclerView..
     */
    //==============================================================================================
    override fun getItemCount(): Int {
        return cityList.size
    }

    //==============================================================================================
    /**
     * onBindViewHolder Function for RecyclerView..
     */
    //==============================================================================================
    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(cityList[position], position, listener)
    }


}
