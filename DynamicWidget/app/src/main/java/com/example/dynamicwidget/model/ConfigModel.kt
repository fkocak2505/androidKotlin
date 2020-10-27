package com.example.dynamicwidget.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConfigModel {

    @SerializedName("widgets")
    @Expose
    val widgets: MutableList<WidgetsData>? = null

}