package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.changePassword

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword

interface IChangePasswordActivityView {

    fun initChangePasswordActivityComponent()

    fun changeComponentTypeface()

    fun clickChangePassword()

    fun handleChangePasswordAPIResult(response4ChangePassword: Response4ChangePassword)

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(response4ErrorObj : Response4ErrorObj)

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

}