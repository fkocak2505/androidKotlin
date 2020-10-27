package com.onedio.androidside.mvp.model.dashboard.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ValOfUserProfileInfoData {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("reputation")
    @Expose
    var reputation: Int? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("occupation")
    @Expose
    var occupation: String? = null

    @SerializedName("birthdate")
    @Expose
    var birthdate: String? = null

    @SerializedName("followerCount")
    @Expose
    var followerCount: String? = null

    @SerializedName("followers")
    @Expose
    var followers: List<Followers>? = null

    @SerializedName("followingCount")
    @Expose
    var followingCount: String? = null

    @SerializedName("followings")
    @Expose
    var followings: List<Followings>? = null

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null

}