package com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword

interface IChangePasswordActivityModel {

    fun initApiService()

    fun setNewUserPasswordParams(userId: String,email: String,userName: String, newPassword: String, oldPassword: String)

    fun changeUserPassword(userId: String, email: String,userName: String, newPassword: String, oldPassword: String, iRequestListener: IRequestListener<Response4ChangePassword>)

}