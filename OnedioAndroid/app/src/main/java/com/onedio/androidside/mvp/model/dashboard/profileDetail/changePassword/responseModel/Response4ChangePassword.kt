package com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4ChangePassword {

    @SerializedName("status")
    @Expose
    var status: ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    var data: String? = null

}