package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Reactions {

    @SerializedName("code")
    @Expose
    val reactionCode : String? = null

    @SerializedName("count")
    @Expose
    val reactionCount : Int? = null

    @SerializedName("icons")
    @Expose
    val icons : ReactionsIcon? = null

}