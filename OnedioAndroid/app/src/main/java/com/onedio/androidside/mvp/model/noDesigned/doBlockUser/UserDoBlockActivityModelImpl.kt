package com.onedio.androidside.mvp.model.noDesigned.doBlockUser

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.singleton.OnedioSingletonPattern

class UserDoBlockActivityModelImpl :
    IUserDoBlockActivityModel {

    private lateinit var iApiService: IApiService


    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun doBlockUser(userName: String, iRequestListener: IRequestListener<Response4DoBlockedUser>) {

        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doUserBlocked(
            token,
            "Bearer $accessToken",
            userName
        ).enqueue(object : retrofit2.Callback<Response4DoBlockedUser> {
            override fun onFailure(call: Call<Response4DoBlockedUser>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(call: Call<Response4DoBlockedUser>?, response: Response<Response4DoBlockedUser>?) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })

    }
}