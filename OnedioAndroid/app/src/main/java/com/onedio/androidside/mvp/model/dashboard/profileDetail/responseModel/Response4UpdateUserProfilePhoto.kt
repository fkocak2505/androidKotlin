package com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4UpdateUserProfilePhoto {

    @SerializedName("status")
    @Expose
    var status : ValOfUpdateUserProfileInfoStatus? = null

    @SerializedName("data")
    @Expose
    var data : String? = null



}