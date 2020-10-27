package com.onedio.androidside.mvp.model.loginAndRegister

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.loginAndRegister.requestModel.Request4Token
import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token

class LoginAndRegisterDashboardActivityModelImpl :
    ILoginAndRegisterDashboardActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    /// Request Params
    private lateinit var request4Token: Request4Token

    //==================================================================================================================
    /**
     * Init Api Service
     */
    //==================================================================================================================
    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    //==================================================================================================================
    /**
     * Set Token Params 4 Request
     */
    //==================================================================================================================
    override fun setTokenParams(clientId: String, clientSecret: String, grantType: String) {
        gson = Gson()
        request4Token =
            Request4Token()
        request4Token.clientId = clientId
        request4Token.clientSecret = clientSecret
        request4Token.grantType = grantType
    }

    //==================================================================================================================
    /**
     * Model Request of Token
     */
    //==================================================================================================================
    override fun getToken(iRequestResultListener: IRequestListener<Response4Token>) {
        initApiService()

        setTokenParams("12", "12", "client_credentials")

        iApiService.getToken(RequestBody.create(MediaType.parse("application/json"), gson.toJson(request4Token)))
            .enqueue(object : retrofit2.Callback<Response4Token> {
                override fun onFailure(call: Call<Response4Token>?, t: Throwable?) {
                    iRequestResultListener.onFail(t)
                }

                override fun onResponse(call: Call<Response4Token>?, response: Response<Response4Token>?) {
                    when (response?.isSuccessful) {
                        true -> iRequestResultListener.onSuccess(response)
                        false -> iRequestResultListener.onUnSuccess(response)
                    }
                }
            })
    }
}