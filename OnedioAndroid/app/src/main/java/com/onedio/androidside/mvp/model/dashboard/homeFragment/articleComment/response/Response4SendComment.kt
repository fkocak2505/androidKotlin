package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4SendComment {

    @SerializedName("message")
    @Expose
    val message: String? = null

    @SerializedName("data")
    @Expose
    val data: DataOfSendComment? = null

}