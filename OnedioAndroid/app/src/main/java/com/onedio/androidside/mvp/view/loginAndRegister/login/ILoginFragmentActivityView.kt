package com.onedio.androidside.mvp.view.loginAndRegister.login

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login

interface ILoginFragmentActivityView {

    fun initLoginFragmentActivityComponent()

    fun changeComponentTextFonts()

    fun edtOfTouchListener()

    fun clickLoginButton()

    fun clickLoginButtonWithFB()

    fun clickForgotPassword()

    fun clickEmptyArea()

    fun handleSuccessLoginResponse(response4Login: Response4Login)

    fun handleUserProfileInfoData(response4UserProfileInfo: Response4UserProfileInfo)

    fun handleUsersProfileData(response4UsersProfile: Response4UsersProfile)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun checkWarningMessage(ePosta: String, password: String): Boolean

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

}