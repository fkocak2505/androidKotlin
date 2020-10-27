package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation
import com.onedio.androidside.singleton.OnedioSingletonPattern

class MessageActivityModelImpl :
    IMessageActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun getUserAllConversation(iRequestListener: IRequestListener<Response4UserAllConversation>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getUserAllConversation(
            token,
            "Bearer $accessToken"
        ).enqueue(object : retrofit2.Callback<Response4UserAllConversation> {
            override fun onFailure(call: Call<Response4UserAllConversation>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserAllConversation>?,
                response: Response<Response4UserAllConversation>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun deleteConversation(
        deletingConversationID: String,
        iRequestListener: IRequestListener<Response4DeleteConversation>
    ) {

        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.deleteConversation(
            token,
            "Bearer $accessToken",
            deletingConversationID
        ).enqueue(object : retrofit2.Callback<Response4DeleteConversation> {
            override fun onFailure(call: Call<Response4DeleteConversation>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4DeleteConversation>?,
                response: Response<Response4DeleteConversation>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }
}