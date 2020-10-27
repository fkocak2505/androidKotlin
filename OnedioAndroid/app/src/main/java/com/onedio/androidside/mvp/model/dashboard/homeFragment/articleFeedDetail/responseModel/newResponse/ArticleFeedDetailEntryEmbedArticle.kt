package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleBadgeModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleCategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleStatsModel

class ArticleFeedDetailEntryEmbedArticle {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("badge")
    @Expose
    val badge: MutableList<ArticleBadgeModel>? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("stats")
    @Expose
    val stats: ArticleStatsModel? = null

    @SerializedName("publishDate")
    @Expose
    val createDate: String? = null

    @SerializedName("categories")
    @Expose
    val categories: MutableList<ArticleCategoryModel>? = null

}