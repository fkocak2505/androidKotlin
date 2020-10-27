package com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.BadgeData
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.DateOfArticle
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

class ArticleOfCategoryDetailItem {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfArticle? = null

    @SerializedName("dates")
    @Expose
    val dates: DateOfArticle? = null

    @SerializedName("stats")
    @Expose
    val stats: StatsOfCategoryDetailItem? = null

    @SerializedName("badge")
    @Expose
    val badge: BadgeData? = null

    /*@SerializedName("reactions")
    @Expose
    val reactions: String? = null*/

}