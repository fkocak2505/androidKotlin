package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Articles {

    @SerializedName("image")
    @Expose
    var image: ImageOfSearchArticle? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("legacyId")
    @Expose
    var legacyId: Long? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("stats")
    @Expose
    var stats: StatsOfSearchArticle? = null

    @SerializedName("showInWebview")
    @Expose
    var showInWebview: Boolean? = null



}