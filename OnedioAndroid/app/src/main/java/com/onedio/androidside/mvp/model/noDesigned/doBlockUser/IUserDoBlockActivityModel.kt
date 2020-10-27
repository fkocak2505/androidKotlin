package com.onedio.androidside.mvp.model.noDesigned.doBlockUser

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser

interface IUserDoBlockActivityModel {

    fun initApiService()

    fun doBlockUser(userName: String,iRequestListener: IRequestListener<Response4DoBlockedUser>)

}