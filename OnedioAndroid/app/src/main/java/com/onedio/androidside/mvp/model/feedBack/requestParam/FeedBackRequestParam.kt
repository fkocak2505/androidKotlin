package com.onedio.androidside.mvp.model.feedBack.requestParam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FeedBackRequestParam {

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("device_os")
    @Expose
    var deviceOs: String? = null

    @SerializedName("device_model")
    @Expose
    var deviceModel: String? = null

    @SerializedName("app_version")
    @Expose
    var appVersion: String? = null

    @SerializedName("os_version")
    @Expose
    var osVersion: String? = null






}