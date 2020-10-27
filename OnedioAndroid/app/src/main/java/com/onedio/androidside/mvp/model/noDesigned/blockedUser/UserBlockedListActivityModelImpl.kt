package com.onedio.androidside.mvp.model.noDesigned.blockedUser

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.singleton.OnedioSingletonPattern

class UserBlockedListActivityModelImpl:
    IUserBlockedListActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun getUserBlockedListData(iRequestListener: IRequestListener<Response4UserBlockedList>) {

        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getUserBlockedListData(
            token,
            "Bearer $accessToken").enqueue(object : retrofit2.Callback<Response4UserBlockedList>{
            override fun onFailure(call: Call<Response4UserBlockedList>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserBlockedList>?,
                response: Response<Response4UserBlockedList>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })

    }
}