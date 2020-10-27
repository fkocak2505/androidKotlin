package com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4MostSharedArticle {

    @SerializedName("code")
    @Expose
    val code: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

    @SerializedName("data")
    @Expose
    val data: DataOfMostSharedArticle? = null

}