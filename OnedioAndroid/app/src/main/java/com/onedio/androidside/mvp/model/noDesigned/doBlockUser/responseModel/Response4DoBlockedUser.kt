package com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4DoBlockedUser {

    @SerializedName("status")
    @Expose
    var status: ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    var isBlocked: Boolean? = null



}