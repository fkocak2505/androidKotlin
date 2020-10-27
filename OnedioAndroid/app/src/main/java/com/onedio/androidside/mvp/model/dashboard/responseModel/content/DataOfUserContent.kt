package com.onedio.androidside.mvp.model.dashboard.responseModel.content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DataOfUserContent {

    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("legacyId")
    @Expose
    val legacyId: Int? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("image")
    @Expose
    val image: ImageOfUserContent? = null

}