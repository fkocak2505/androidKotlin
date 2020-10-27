package com.onedio.androidside.mvp.model.loginAndRegister.login

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login

interface ILoginFragmentActivityModel {

    fun initApiService()

    fun setLoginParams(email: String, password: String)

    fun setFBLoginParams(token: String)

    fun doLogin(email: String, password: String, iRequestResultListener: IRequestListener<Response4Login>)

    fun doLoginWithFB(token: String, iRequestResultListener: IRequestListener<Response4Login>)

}