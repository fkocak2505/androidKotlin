package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleAuthorsStatsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel

class ArticleFeedUserModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("username")
    @Expose
    val username: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("occupation")
    @Expose
    val occupation: String? = null

    @SerializedName("stats")
    @Expose
    val stats: ArticleAuthorsStatsModel? = null



}