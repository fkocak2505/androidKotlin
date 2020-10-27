package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeletedByKnowledge {

    @SerializedName("deletedByWho")
    @Expose
    val deletedByWho: String? =null

    @SerializedName("deletedAt")
    @Expose
    val deletedAt: String? =null

}