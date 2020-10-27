package com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.requestModel.ForgotPasswordParams
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword
import com.onedio.androidside.singleton.OnedioSingletonPattern

class ForgotPasswordActivityModelImpl :
    IForgotPasswordActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    private lateinit var forgotPasswordParams : ForgotPasswordParams


    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun setEmailForgotParams(email: String) {
        gson = Gson()
        forgotPasswordParams =
            ForgotPasswordParams()
        forgotPasswordParams.email = email
    }


    override fun sendEmail4ForgotPassword(email: String, iRequestListener: IRequestListener<Response4ForgotPassword>) {
        initApiService()

        setEmailForgotParams(email)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.forgotPassword(
            token,
            RequestBody.create(MediaType.parse("application/json"), gson.toJson(forgotPasswordParams))).enqueue(object : retrofit2.Callback<Response4ForgotPassword>{
            override fun onFailure(call: Call<Response4ForgotPassword>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4ForgotPassword>?,
                response: Response<Response4ForgotPassword>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })


    }
}