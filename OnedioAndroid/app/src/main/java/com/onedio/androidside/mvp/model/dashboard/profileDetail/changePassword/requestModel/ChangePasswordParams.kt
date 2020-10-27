package com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordParams {

    @SerializedName("id")
    @Expose
    var id : String? = null

    @SerializedName("email")
    @Expose
    var email : String? = null

    @SerializedName("username")
    @Expose
    var username : String? = null

    @SerializedName("password")
    @Expose
    var password : String? = null

    @SerializedName("oldPassword")
    @Expose
    var oldPassword : String? = null






}