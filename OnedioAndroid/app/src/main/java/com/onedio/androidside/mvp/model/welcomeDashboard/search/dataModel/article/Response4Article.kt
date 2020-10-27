package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Article {

    @SerializedName("articles")
    @Expose
    var articles: MutableList<Articles>? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null

}