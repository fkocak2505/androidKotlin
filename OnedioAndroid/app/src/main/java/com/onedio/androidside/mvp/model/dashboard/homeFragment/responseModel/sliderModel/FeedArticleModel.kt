package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleReactionsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleTopCommentModel

class FeedArticleModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null

    @SerializedName("showInWebview")
    @Expose
    val showInWebview: Boolean? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("videoData")
    @Expose
    val videoData: ArticleVideoModel? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("publishDate")
    @Expose
    val createDate: String? = null

    @SerializedName("stats")
    @Expose
    val stats: ArticleStatsModel? = null

    @SerializedName("tags")
    @Expose
    val tags: MutableList<String>? = null

    @SerializedName("categories")
    @Expose
    val categories: MutableList<ArticleCategoryModel>? = null

    @SerializedName("badge")
    @Expose
    val badge: MutableList<ArticleBadgeModel>? = null

    @SerializedName("topComments")
    @Expose
    val topComments: MutableList<ArticleTopCommentModel>? = null

    @SerializedName("reactions")
    @Expose
    val reactions: MutableList<ArticleReactionsModel>? = null

    @SerializedName("isUserFavorite")
    @Expose
    val isUserFavorite: Boolean? = null

    @SerializedName("redirectUrl")
    @Expose
    val redirectUrl: String? = null




}