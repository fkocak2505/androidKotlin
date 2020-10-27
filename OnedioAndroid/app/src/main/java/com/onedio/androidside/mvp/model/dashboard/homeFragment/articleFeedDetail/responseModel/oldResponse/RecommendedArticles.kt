package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

class RecommendedArticles {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: Long? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfArticle? = null

}