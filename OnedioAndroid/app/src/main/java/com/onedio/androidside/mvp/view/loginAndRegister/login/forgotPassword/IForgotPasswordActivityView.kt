package com.onedio.androidside.mvp.view.loginAndRegister.login.forgotPassword

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword

interface IForgotPasswordActivityView {

    fun initForgotPasswordActivityComponents()

    fun clickSendEmailButton()

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun handleResponseData(response4ForgotPassword: Response4ForgotPassword)

    fun clickEmptyArea()

}