package com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryData {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

}