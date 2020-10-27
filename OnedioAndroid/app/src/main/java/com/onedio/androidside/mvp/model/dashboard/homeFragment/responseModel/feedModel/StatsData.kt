package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatsData {

    @SerializedName("commentCount")
    @Expose
    val commentCount: Int? = null

    @SerializedName("viewCount")
    @Expose
    val viewCount: Int? = null

}