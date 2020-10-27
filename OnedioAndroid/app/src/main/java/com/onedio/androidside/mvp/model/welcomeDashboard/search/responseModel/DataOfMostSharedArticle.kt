package com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataOfMostSharedArticle {

    @SerializedName("articles")
    @Expose
    val articles: MutableList<Article>? = null

}