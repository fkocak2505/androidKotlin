package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.SendDateData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.SenderUserData

class MessagesData {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("text")
    @Expose
    val textOfMessage: String? = null

    @SerializedName("sendDate")
    @Expose
    val sendDate: SendDateData? = null

    @SerializedName("direction")
    @Expose
    val direction: String? = null

    @SerializedName("sender")
    @Expose
    val sender: SenderUserData? = null







}