package com.onedio.androidside.mvp.presenter.dashboard.profileDetail.changePassword

interface IChangePasswordActivityPresenter {

    fun changeUserPassword(userId: String, email:String,userName: String, newPassword: String, oldPassword: String)

}