package com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ValOfUpdateUserProfileInfoStatus {

    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("operationCode")
    @Expose
    var operationCode: String? = null

}