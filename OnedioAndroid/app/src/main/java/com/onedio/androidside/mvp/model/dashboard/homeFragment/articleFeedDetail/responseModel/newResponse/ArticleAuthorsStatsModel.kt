package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleAuthorsStatsModel {

    @SerializedName("followings")
    @Expose
    val followings: Int? = null

    @SerializedName("followers")
    @Expose
    val followers: Int? = null

    @SerializedName("articles")
    @Expose
    val articles: Int? = null

}