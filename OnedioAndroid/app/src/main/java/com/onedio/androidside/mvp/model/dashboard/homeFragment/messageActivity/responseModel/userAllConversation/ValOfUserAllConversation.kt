package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ValOfUserAllConversation {

    @SerializedName("conversations")
    @Expose
    val conversationList: List<ConversationData>? = null

}