package com.onedio.androidside.mvp.view.loginAndRegister.register

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register

interface IRegisterFragmentAcitivityView {

    fun initRegisterFragmentActivityComponent()

    fun changeComponentTextFonts()

    fun clickRegisterButton()

    fun clickEmptyArea()

    //fun clickRegisterButtonWithFB()

    fun handleCheckMail(response4IsMailExist: Response4IsMailExist)

    fun handleCheckUserName(response4IsMailExist: Response4IsMailExist)

    fun handleSuccessRegisterResponse(response4Register: Response4Register)

    //fun handleSuccessRegisterResponseWithFB(response4Login: Response4Login)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun edtOfTouchListener()

    fun checkWarningMessage(ePosta: String, userName: String, name: String, password: String, veriftPassword: String): Boolean

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

    fun goLoginAndRegisterActivity()


}