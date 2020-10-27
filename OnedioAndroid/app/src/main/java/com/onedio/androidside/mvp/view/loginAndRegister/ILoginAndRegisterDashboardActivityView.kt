package com.onedio.androidside.mvp.view.loginAndRegister

import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token


interface ILoginAndRegisterDashboardActivityView {

    fun initLoginAndRegisterDashboardAcitivityComponents()

    fun getToken()

    fun handleTokenResponse(response4Token: Response4Token)

}