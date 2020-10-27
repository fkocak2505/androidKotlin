package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MeData {

    @SerializedName("isRead")
    @Expose
    val isRead: Boolean? = null

    @SerializedName("isChecked")
    @Expose
    val isChecked: Boolean? = null

    @SerializedName("user")
    @Expose
    val user: SenderUserData? = null

}