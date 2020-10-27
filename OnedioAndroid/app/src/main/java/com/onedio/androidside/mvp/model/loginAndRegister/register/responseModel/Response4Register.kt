package com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Register {

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