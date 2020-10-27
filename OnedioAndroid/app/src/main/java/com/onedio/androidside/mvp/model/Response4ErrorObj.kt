package com.onedio.androidside.mvp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus

class Response4ErrorObj {

    @SerializedName("status")
    @Expose
    var status: ValOfUpdateUserProfileInfoStatus? = null

}