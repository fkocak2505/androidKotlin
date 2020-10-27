package com.onedio.androidside.mvp.model.loginAndRegister.login.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Request4Login {

    @SerializedName("grant_type")
    @Expose
    var grantType: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}