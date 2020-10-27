package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataOfSendMessage {

    @SerializedName("success")
    @Expose
    val success: Boolean? = null

    @SerializedName("sendedMessage")
    @Expose
    val sendedMessage: String? = null

}