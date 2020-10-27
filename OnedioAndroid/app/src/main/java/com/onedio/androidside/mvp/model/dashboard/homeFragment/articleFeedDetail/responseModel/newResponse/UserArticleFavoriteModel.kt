package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleBadgeModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleCategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel

class UserArticleFavoriteModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("createDate")
    @Expose
    val createDate: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null


    @SerializedName("title")
    @Expose
    val title: String? = null


    @SerializedName("categories")
    @Expose
    val categories: MutableList<ArticleCategoryModel>? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("authors")
    @Expose
    val authors: MutableList<ArticleFeedDetailsAuthors>? = null

    @SerializedName("badge")
    @Expose
    val badge: MutableList<ArticleBadgeModel>? = null

    @SerializedName("showInWebview")
    @Expose
    val showInWebview: Boolean? = null

    @SerializedName("redirectUrl")
    @Expose
    val redirectUrl: String? = null





}