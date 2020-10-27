package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageOfSlider {

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("alternates")
    @Expose
    val alternates: Alternates? = null

}