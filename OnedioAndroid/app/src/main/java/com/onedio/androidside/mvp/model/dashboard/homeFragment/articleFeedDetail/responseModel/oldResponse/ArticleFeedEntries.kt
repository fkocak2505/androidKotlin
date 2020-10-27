package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.DateOfArticle
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

class ArticleFeedEntries {

    @SerializedName("id")
    @Expose
    val id: String? = null


    @SerializedName("dates")
    @Expose
    val dates: DateOfArticle? = null

    @SerializedName("mode")
    @Expose
    val mode: String? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfArticle? = null

    @SerializedName("data")
    @Expose
    val data: EntriesData? = null

}