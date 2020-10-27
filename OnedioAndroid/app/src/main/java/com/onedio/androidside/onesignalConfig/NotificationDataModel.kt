package com.onedio.androidside.onesignalConfig

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationDataModel {

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("legacyid")
    @Expose
    val legacyid: String? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("isTest")
    @Expose
    val isTest: String? = null


}