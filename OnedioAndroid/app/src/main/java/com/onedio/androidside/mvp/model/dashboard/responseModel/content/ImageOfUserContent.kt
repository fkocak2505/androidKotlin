package com.onedio.androidside.mvp.model.dashboard.responseModel.content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ImageOfUserContent {

    @SerializedName("url")
    @Expose
    val url: String? = null
    @SerializedName("height")
    @Expose
    val height: Int? = null
    @SerializedName("width")
    @Expose
    val width: Int? = null
    @SerializedName("format")
    @Expose
    val format: String? = null
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Any? = null

}