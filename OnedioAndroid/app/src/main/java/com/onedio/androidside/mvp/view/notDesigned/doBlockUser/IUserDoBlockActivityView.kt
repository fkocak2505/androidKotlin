package com.onedio.androidside.mvp.view.notDesigned.doBlockUser

import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser

interface IUserDoBlockActivityView {

    fun initUserDoBlockActivityComponent()

    fun clickDoBlockUser()

    fun doBlockUser()

    fun handleDoUserBlockedInfoData(response4DoBlockedUser: Response4DoBlockedUser)

    fun showLoading()

    fun hideLoading()



}