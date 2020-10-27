package com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {

    @SerializedName("id")
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

    @SerializedName("dates")
    @Expose
    val dates: DateOfArticle? = null




}