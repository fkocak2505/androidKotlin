package com.onedio.androidside.mvp.model.noDesigned.blockedUser

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList

interface IUserBlockedListActivityModel {

    fun initApiService()

    fun getUserBlockedListData(iRequestListener: IRequestListener<Response4UserBlockedList>)

}