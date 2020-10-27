package com.onedio.androidside.mvp.model.loginAndRegister.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Request4Token {

    @SerializedName("client_id")
    @Expose
    var clientId: String? = null

    @SerializedName("client_secret")
    @Expose
    var clientSecret: String? = null

    @SerializedName("grant_type")
    @Expose
    var grantType: String? = null

}