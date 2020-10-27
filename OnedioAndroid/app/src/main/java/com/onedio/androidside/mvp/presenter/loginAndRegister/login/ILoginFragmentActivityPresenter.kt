package com.onedio.androidside.mvp.presenter.loginAndRegister.login

interface ILoginFragmentActivityPresenter {

    fun doLogin(email: String, password: String)

    fun doLoginWithFB(token: String)

}