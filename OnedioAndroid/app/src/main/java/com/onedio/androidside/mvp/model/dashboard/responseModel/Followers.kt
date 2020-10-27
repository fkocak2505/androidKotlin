package com.onedio.androidside.mvp.model.dashboard.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Followers {

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("avatarUrl")
    @Expose
    var avatarUrl: String? = null

}