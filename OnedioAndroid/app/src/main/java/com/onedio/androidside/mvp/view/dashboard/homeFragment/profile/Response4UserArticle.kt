package com.onedio.androidside.mvp.view.dashboard.homeFragment.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel

class Response4UserArticle {

    @SerializedName("data")
    @Expose
    val data: MutableList<FeedArticleModel>? = null

}