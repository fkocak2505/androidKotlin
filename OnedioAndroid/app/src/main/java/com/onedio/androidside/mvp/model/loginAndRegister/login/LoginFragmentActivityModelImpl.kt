package com.onedio.androidside.mvp.model.loginAndRegister.login

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.loginAndRegister.login.requestModel.Request4FBLogin
import com.onedio.androidside.mvp.model.loginAndRegister.login.requestModel.Request4Login
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.singleton.OnedioSingletonPattern

class LoginFragmentActivityModelImpl :
    ILoginFragmentActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    /// Request Params
    private lateinit var request4Login: Request4Login
    private lateinit var request4FBLogin: Request4FBLogin

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
     * Set Login Params
     */
    //==================================================================================================================
    override fun setLoginParams(email: String, password: String) {
        gson = Gson()
        request4Login =
            Request4Login()
        request4Login.grantType = "password"
        request4Login.email = email
        request4Login.password = password
    }

    //==================================================================================================================
    /**
     * Set FB Login Params
     */
    //==================================================================================================================
    override fun setFBLoginParams(token: String) {
        gson = Gson()
        request4FBLogin =
            Request4FBLogin()
        request4FBLogin.grantType = "token"
        request4FBLogin.provider = "facebook"
        request4FBLogin.token = token
    }

    //==================================================================================================================
    /**
     * Model Request 4 Login
     */
    //==================================================================================================================
    override fun doLogin(email: String, password: String, iRequestResultListener: IRequestListener<Response4Login>) {
        initApiService()

        setLoginParams(email, password)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doLogin(
            token,
            RequestBody.create(MediaType.parse("application/json"), gson.toJson(request4Login))
        ).enqueue(object : retrofit2.Callback<Response4Login> {
            override fun onFailure(call: Call<Response4Login>?, t: Throwable?) {
                iRequestResultListener.onFail(t)
            }

            override fun onResponse(call: Call<Response4Login>?, response: Response<Response4Login>?) {
                when (response?.isSuccessful) {
                    true -> iRequestResultListener.onSuccess(response)
                    false -> iRequestResultListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun doLoginWithFB(token: String, iRequestResultListener: IRequestListener<Response4Login>) {
        initApiService()

        setFBLoginParams(token)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doLogin(
            token,
            RequestBody.create(MediaType.parse("application/json"), gson.toJson(request4FBLogin))).enqueue(object : retrofit2.Callback<Response4Login>{
            override fun onFailure(call: Call<Response4Login>?, t: Throwable?) {
                iRequestResultListener.onFail(t)
            }

            override fun onResponse(call: Call<Response4Login>?, response: Response<Response4Login>?) {
                when (response?.isSuccessful) {
                    true -> iRequestResultListener.onSuccess(response)
                    false -> iRequestResultListener.onUnSuccess(response)
                }
            }
        })
    }
}