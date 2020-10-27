package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendCommentParam {

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("parent")
    @Expose
    var parent: String? = null

    @SerializedName("replyTo")
    @Expose
    var replyTo: String? = null

}