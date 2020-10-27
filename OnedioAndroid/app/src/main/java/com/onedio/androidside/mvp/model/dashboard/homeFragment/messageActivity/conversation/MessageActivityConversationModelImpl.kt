package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation
import com.onedio.androidside.singleton.OnedioSingletonPattern

class MessageActivityConversationModelImpl :
    IMessageActivityConversationModel {

    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }


    override fun getConversationIDMessages(
        conversationID: String,
        iRequestListener: IRequestListener<Response4ConversationIDMessage>
    ) {

        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getConversationIDMessages(
            token,
            "Bearer $accessToken",
            conversationID
        ).enqueue(object : retrofit2.Callback<Response4ConversationIDMessage> {
            override fun onFailure(call: Call<Response4ConversationIDMessage>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4ConversationIDMessage>?,
                response: Response<Response4ConversationIDMessage>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })

    }

    override fun sendMessage(
        message: String,
        conversationID: String,
        iRequestListener: IRequestListener<Response4SendMessage>
    ) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.sendMessage(
            token,
            "Bearer $accessToken",
            conversationID,
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("message", message)
                .build()
        ).enqueue(object : retrofit2.Callback<Response4SendMessage> {
            override fun onFailure(call: Call<Response4SendMessage>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4SendMessage>?,
                response: Response<Response4SendMessage>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun startConversation(iRequestListener: IRequestListener<Response4StartConversation>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.startConversation(
            token,
            "Bearer $accessToken",
            OnedioSingletonPattern.instance.getAnotherUserId()
        ).enqueue(object : retrofit2.Callback<Response4StartConversation> {
            override fun onFailure(call: Call<Response4StartConversation>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4StartConversation>?,
                response: Response<Response4StartConversation>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })


    }


}