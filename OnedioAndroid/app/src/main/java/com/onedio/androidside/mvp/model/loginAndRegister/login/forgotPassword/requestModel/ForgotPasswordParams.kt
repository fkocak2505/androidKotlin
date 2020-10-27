package com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.requestModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForgotPasswordParams {

    @SerializedName("email")
    @Expose
    var email : String? = null

}