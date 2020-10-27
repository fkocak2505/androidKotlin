package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleCategoryModel {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("icons")
    @Expose
    val icons: ArticleCategoryIconsModel? = null

}