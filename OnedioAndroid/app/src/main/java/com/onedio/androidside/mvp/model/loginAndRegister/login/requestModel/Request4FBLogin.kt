package com.onedio.androidside.mvp.model.loginAndRegister.login.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Request4FBLogin {

    @SerializedName("grant_type")
    @Expose
    var grantType: String? = null

    @SerializedName("provider")
    @Expose
    var provider: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null
}