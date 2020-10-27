package com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Login{

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null

    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null

    @SerializedName("expires_in")
    @Expose
    var expiresIn: String? = null

    @SerializedName("refresh_token")
    @Expose
    var refreshToken: String? = null

    @SerializedName("operationCode")
    @Expose
    var operationCode: String? = null

}