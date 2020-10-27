package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4UserAllConversation {

    @SerializedName("status")
    @Expose
    val status: ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    val dataOfUserAllConversation: ValOfUserAllConversation? = null

}