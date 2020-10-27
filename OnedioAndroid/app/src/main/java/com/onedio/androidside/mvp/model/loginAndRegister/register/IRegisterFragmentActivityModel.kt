package com.onedio.androidside.mvp.model.loginAndRegister.register

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register

interface IRegisterFragmentActivityModel {

    fun initApiService()

    fun setRegisterParams(userName: String, email: String, name: String, password: String)

    fun setFBRegisterParam(token: String)

    fun isMailExist(email: String, iRequestListener: IRequestListener<Response4IsMailExist>)

    fun isUserNameExist(userName: String, iRequestListener: IRequestListener<Response4IsMailExist>)

    fun doRegister(userName: String, email: String, name: String, password: String, iRequestListener: IRequestListener<Response4Register>)

    fun doRegisterWithFB(token: String, iRequestListener: IRequestListener<Response4Login>)

}