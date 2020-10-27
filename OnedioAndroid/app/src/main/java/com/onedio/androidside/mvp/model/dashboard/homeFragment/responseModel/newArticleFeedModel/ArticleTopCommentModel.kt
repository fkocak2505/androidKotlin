package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleTopCommentModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("createDate")
    @Expose
    val createDate: String? = null

    @SerializedName("user")
    @Expose
    val user: ArticleFeedUserModel? = null

    @SerializedName("ratings")
    @Expose
    val ratings: ArticleFeedAnonRatingsModel? = null

}