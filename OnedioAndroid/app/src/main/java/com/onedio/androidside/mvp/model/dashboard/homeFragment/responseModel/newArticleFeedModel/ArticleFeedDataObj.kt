package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel

class ArticleFeedDataObj {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("feed")
    @Expose
    val feed: MutableList<FeedArticleModel>? = null

}