package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Headlines {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfSlider? = null

    @SerializedName("headlineIndex")
    @Expose
    val headlineIndex: Int? = null



}