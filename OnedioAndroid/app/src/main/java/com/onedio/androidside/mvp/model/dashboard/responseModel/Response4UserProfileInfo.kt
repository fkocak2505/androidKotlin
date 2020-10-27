package com.onedio.androidside.mvp.model.dashboard.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4UserProfileInfo {

    @SerializedName("status")
    @Expose
    var status: ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    var valOfUserProfileInfoData: ValOfUserProfileInfoData? = null

}