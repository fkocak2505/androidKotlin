package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WidgetsData {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("order")
    @Expose
    val order: Int? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("count")
    @Expose
    val count: Int? = null

    @SerializedName("targetId")
    @Expose
    val targetId: String? = null

    @SerializedName("sort")
    @Expose
    val sort: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

}