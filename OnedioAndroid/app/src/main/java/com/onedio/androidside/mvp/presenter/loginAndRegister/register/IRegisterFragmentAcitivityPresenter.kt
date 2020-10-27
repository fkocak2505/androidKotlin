package com.onedio.androidside.mvp.presenter.loginAndRegister.register

interface IRegisterFragmentAcitivityPresenter {

    fun isMailExist(email: String)

    fun isUserNameExist(userName: String)

    fun doRegister(userName: String, email: String, name: String, password: String)

    fun doRegisterWithFB(token: String)

}