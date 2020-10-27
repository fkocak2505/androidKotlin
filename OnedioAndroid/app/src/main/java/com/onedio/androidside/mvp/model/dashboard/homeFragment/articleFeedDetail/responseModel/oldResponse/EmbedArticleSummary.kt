package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.StatsData
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.DateOfArticle
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

class EmbedArticleSummary {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("dates")
    @Expose
    val dates: DateOfArticle? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfArticle? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: String? = null

    @SerializedName("stats")
    @Expose
    val stats: StatsData? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

}