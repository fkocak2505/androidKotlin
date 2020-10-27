package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryArticle {

    @SerializedName("categoryId")
    @Expose
    val categoryId: String? = null

    @SerializedName("categoryName")
    @Expose
    val categoryName: String? = null

    @SerializedName("pngFileUrl")
    @Expose
    val pngFileUrl: String? = null

}