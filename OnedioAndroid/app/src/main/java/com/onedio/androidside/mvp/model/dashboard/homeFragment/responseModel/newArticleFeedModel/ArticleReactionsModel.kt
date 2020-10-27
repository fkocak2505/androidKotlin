package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleCategoryIconsModel

class ArticleReactionsModel {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("count")
    @Expose
    val count: Int? = null

    @SerializedName("icons")
    @Expose
    val icons: ArticleCategoryIconsModel? = null



}