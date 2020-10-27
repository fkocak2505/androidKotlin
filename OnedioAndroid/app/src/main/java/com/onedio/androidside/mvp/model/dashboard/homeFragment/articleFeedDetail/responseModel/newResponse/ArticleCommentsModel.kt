package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleFeedAnonRatingsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleFeedUserModel

class ArticleCommentsModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("user")
    @Expose
    val user: ArticleFeedUserModel? = null

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("replies")
    @Expose
    val replies: ArticleCommentReplies? = null

    @SerializedName("createDate")
    @Expose
    val createDate: String? = null

    @SerializedName("parent")
    @Expose
    val parent: String? = null

    @SerializedName("replyTo")
    @Expose
    val replyTo: String? = null

    @SerializedName("ratings")
    @Expose
    val ratings: ArticleFeedAnonRatingsModel? = null

}