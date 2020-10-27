package com.example.dynamicwidget.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WidgetsData {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("wName")
    @Expose
    val wName: String? = null

    @SerializedName("order")
    @Expose
    val order: Int? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("targetId")
    @Expose
    val targetId: String? = null

    @SerializedName("sort")
    @Expose
    val sort: String? = null

    @SerializedName("articleCount")
    @Expose
    val articleCount: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

}