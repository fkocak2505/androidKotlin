package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.tagsArticle.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.FeedArticles

class Response4TagArticle {

    @SerializedName("data")
    @Expose
    val data: MutableList<FeedArticles>? = null

}