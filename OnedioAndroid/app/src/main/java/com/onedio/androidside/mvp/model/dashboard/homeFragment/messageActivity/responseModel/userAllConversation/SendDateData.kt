package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendDateData {

    @SerializedName("raw")
    @Expose
    var raw: String? = null

    @SerializedName("relative")
    @Expose
    var relative: String? = null

}