package com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataOfCategoryDetailItem {

    @SerializedName("articles")
    @Expose
    val articleOfCategoryDetailItem: MutableList<ArticleOfCategoryDetailItem>? = null

}