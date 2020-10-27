package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4StartConversation {

    @SerializedName("status")
    @Expose
    val status : ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    val dataOfStartConversation : ValOfStartConversationData? = null

}