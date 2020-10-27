package com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateUserProfilePhotoParams {

    @SerializedName("image")
    @Expose
    var image : String? = null

}