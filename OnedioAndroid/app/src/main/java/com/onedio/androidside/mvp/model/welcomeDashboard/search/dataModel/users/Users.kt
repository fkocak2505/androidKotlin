package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Users {

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: UserAvatar? = null

}