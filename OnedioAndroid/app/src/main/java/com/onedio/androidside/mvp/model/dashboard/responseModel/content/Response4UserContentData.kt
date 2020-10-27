package com.onedio.androidside.mvp.model.dashboard.responseModel.content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Response4UserContentData {

    @SerializedName("code")
    @Expose
    val code: String? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
    @SerializedName("data")
    @Expose
    val dataOfUserContent: List<DataOfUserContent>? = null

}