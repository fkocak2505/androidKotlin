package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ValOfConversationIDMessageData {

    @SerializedName("conversation")
    @Expose
    val conversationID : String? = null

    @SerializedName("messages")
    @Expose
    val messages : List<MessagesData>? = null



}