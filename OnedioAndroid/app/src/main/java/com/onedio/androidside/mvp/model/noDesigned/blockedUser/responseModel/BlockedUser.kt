package com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BlockedUser(var valOfUserName: String, var valOfName: String, var valOfAvatar: String) {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null


}