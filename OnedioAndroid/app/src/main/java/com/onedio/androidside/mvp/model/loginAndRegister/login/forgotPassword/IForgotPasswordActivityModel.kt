package com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword

interface IForgotPasswordActivityModel {

    fun initApiService()

    fun setEmailForgotParams(email: String)

    fun sendEmail4ForgotPassword(email: String, iRequestListener: IRequestListener<Response4ForgotPassword>)

}