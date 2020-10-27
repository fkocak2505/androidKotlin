package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleReactionsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleTopCommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleBadgeModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleCategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleStatsModel

class ArticleFeedDetailsModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("authors")
    @Expose
    val authors: MutableList<ArticleFeedDetailsAuthors>? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("categories")
    @Expose
    val categories: MutableList<ArticleCategoryModel>? = null

    @SerializedName("showInWebview")
    @Expose
    val showInWebview: Boolean? = null

    @SerializedName("stats")
    @Expose
    val stats: ArticleStatsModel? = null

    @SerializedName("flags")
    @Expose
    val flags: ArticleFlagsModel? = null

    @SerializedName("tags")
    @Expose
    val tags: MutableList<String>? = null

    @SerializedName("badge")
    @Expose
    val badge: MutableList<ArticleBadgeModel>? = null

    @SerializedName("entries")
    @Expose
    val entries: MutableList<ArticleFeedDetailsEntryModel>? = null

    @SerializedName("topComments")
    @Expose
    val topComments: MutableList<ArticleTopCommentModel>? = null

    @SerializedName("comments")
    @Expose
    val comments: MutableList<ArticleTopCommentModel>? = null

    @SerializedName("reactions")
    @Expose
    val reactions: MutableList<ArticleReactionsModel>? = null

    @SerializedName("publishDate")
    @Expose
    val createDate: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("isUserFavorite")
    @Expose
    val isUserFavorite: Boolean? = null

    @SerializedName("redirectUrl")
    @Expose
    val redirectUrl: Boolean? = null









}