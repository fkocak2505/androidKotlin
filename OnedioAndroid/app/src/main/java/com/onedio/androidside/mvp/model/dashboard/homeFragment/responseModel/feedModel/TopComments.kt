package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TopComments : Parcelable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user")
    @Expose
    var user: UserComment? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("likes")
    @Expose
    var likes: Int? = null

    @SerializedName("createDate")
    @Expose
    var createDate: String? = null

}