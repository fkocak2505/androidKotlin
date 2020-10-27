package com.onedio.androidside.remoteConfig

import com.google.gson.annotations.SerializedName

class RemoteConfigModel {

    @SerializedName("forceUpdateMessage")
    val forceUpdateMessage: String? = null

    @SerializedName("defaultMessage")
    val defaultMessage: String? = null

    @SerializedName("latestAndroid")
    val latestAndroid: String? = null

    @SerializedName("requiredVersionsAndroid")
    val requiredVersionsAndroid: MutableList<String>? = null

}