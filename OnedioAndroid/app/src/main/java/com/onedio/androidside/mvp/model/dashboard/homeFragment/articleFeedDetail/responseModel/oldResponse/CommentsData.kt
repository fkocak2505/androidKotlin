package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentsData {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("user")
    @Expose
    val user: UserData4Info? = null

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("likes")
    @Expose
    val likes: Int? = null

    @SerializedName("createDate")
    @Expose
    val createDate: String? = null

    @SerializedName("children")
    @Expose
    val children: MutableList<CommentsData>? = null

}