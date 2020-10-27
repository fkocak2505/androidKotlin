package com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4IsMailExist {

    @SerializedName("data")
    @Expose
    var data: ValOfIsMailExistData? = null

}