package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleStatsModel {

    @SerializedName("comments")
    @Expose
    val comments: Int? = null

    @SerializedName("reactions")
    @Expose
    val reactions: Int? = null

    @SerializedName("views")
    @Expose
    val viewsTotal: Int? = null



}