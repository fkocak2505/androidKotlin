package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4LikeAndUnLike {

    @SerializedName("code")
    @Expose
    val code: String? = null

    @SerializedName("data")
    @Expose
    val data: Boolean? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

}