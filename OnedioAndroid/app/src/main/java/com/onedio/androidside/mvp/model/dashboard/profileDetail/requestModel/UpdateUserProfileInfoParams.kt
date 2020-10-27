package com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateUserProfileInfoParams {

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("birthdate")
    @Expose
    var birthdate: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

}