package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SenderUserData {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("username")
    @Expose
    val userName: String? = null

    @SerializedName("avatar")
    @Expose
    val avatar: String? = null

}