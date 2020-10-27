package com.onedio.androidside.mvp.model.loginAndRegister

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token

interface ILoginAndRegisterDashboardActivityModel {

    fun initApiService()

    fun setTokenParams(clientId: String, clientSecret: String, grantType: String)

    fun getToken(iRequestResultListener: IRequestListener<Response4Token>)

}