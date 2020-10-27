package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataOfFeedModel {

    @SerializedName("stories")
    @Expose
    val stories: MutableList<Stories>? = null

    @SerializedName("headlines")
    @Expose
    val headlines: MutableList<Headlines>? = null

    @SerializedName("articles")
    @Expose
    val articles: MutableList<FeedArticles>? = null

}