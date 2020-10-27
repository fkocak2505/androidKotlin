package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageData {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("sendDate")
    @Expose
    val sendDate: SendDateData? = null

    @SerializedName("direction")
    @Expose
    val direction: String? = null

    @SerializedName("sender")
    @Expose
    val sender: SenderData? = null

}