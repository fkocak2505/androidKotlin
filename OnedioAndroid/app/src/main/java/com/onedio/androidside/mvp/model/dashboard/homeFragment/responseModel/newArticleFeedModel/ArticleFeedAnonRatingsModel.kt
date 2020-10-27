package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleFeedAnonRatingsModel {

    @SerializedName("dislikes")
    @Expose
    val dislikes: Int? = null

    @SerializedName("likes")
    @Expose
    val likes: Int? = null

}