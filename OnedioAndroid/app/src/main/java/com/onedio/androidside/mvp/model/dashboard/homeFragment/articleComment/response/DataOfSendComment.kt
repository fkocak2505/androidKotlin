package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataOfSendComment {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("user")
    @Expose
    val user: String? = null

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("replyTo")
    @Expose
    val replyTo: String? = null

    @SerializedName("parent")
    @Expose
    val parent: String? = null

    @SerializedName("likes")
    @Expose
    val likes: Int? = null

    @SerializedName("dislikes")
    @Expose
    val dislikes: Int? = null

    @SerializedName("createDate")
    @Expose
    val createDate: String? = null

}