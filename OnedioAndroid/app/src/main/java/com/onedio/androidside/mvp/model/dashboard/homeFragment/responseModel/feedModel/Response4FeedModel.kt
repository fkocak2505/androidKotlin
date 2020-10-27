package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4FeedModel {

    @SerializedName("code")
    @Expose
    val code: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

    @SerializedName("data")
    @Expose
    val dataOfFeedModel: DataOfFeedModel? = null

}