package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConfigWidgetsArray {

    @SerializedName("widgets")
    @Expose
    val widgets: MutableList<WidgetsData>? = null

}