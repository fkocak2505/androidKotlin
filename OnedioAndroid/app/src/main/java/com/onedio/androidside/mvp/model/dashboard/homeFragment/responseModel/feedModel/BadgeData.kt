package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BadgeData {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("badgeName")
    @Expose
    val badgeName: String? = null

    @SerializedName("svgFileUrl")
    @Expose
    val svgFileUrl: String? = null

    @SerializedName("pngFileUrl")
    @Expose
    val pngFileUrl: String? = null

}