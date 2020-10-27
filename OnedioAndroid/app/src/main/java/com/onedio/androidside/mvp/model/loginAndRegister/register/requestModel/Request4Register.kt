package com.onedio.androidside.mvp.model.loginAndRegister.register.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Request4Register {

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("confirmPassword")
    @Expose
    var confirmPassword: String? = null

}