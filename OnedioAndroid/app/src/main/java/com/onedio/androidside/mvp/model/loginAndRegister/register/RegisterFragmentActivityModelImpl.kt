package com.onedio.androidside.mvp.model.loginAndRegister.register

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
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.model.loginAndRegister.register.requestModel.Request4Register
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register
import com.onedio.androidside.singleton.OnedioSingletonPattern

class RegisterFragmentActivityModelImpl :
    IRegisterFragmentActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    /// Request Param
    private lateinit var request4Register: Request4Register
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
     * set register Params 4 Request
     */
    //==================================================================================================================
    override fun setRegisterParams(
        userName: String,
        email: String,
        name: String,
        password: String
    ) {
        gson = Gson()
        request4Register =
            Request4Register()
        request4Register.username = userName
        request4Register.email = email
        request4Register.name = name
        request4Register.password = password
        request4Register.confirmPassword = password
    }

    //==================================================================================================================
    /**
     * set Facebook Register Params 4 Request
     */
    //==================================================================================================================
    override fun setFBRegisterParam(token: String) {
        gson = Gson()
        request4FBLogin =
            Request4FBLogin()
        request4FBLogin.grantType = "token"
        request4FBLogin.provider = "facebook"
        request4FBLogin.token = token
    }

    override fun isMailExist(
        email: String,
        iRequestListener: IRequestListener<Response4IsMailExist>
    ) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.isMailExist(token, email)
            .enqueue(object : retrofit2.Callback<Response4IsMailExist> {
                override fun onFailure(call: Call<Response4IsMailExist>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4IsMailExist>?,
                    response: Response<Response4IsMailExist>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun isUserNameExist(
        userName: String,
        iRequestListener: IRequestListener<Response4IsMailExist>
    ) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.isUserNameExist(token, userName)
            .enqueue(object : retrofit2.Callback<Response4IsMailExist> {
                override fun onFailure(call: Call<Response4IsMailExist>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4IsMailExist>?,
                    response: Response<Response4IsMailExist>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    //==================================================================================================================
    /**
     * Model Request 4 Register
     */
    //==================================================================================================================
    override fun doRegister(
        userName: String,
        email: String,
        name: String,
        password: String,
        iRequestListener: IRequestListener<Response4Register>
    ) {
        initApiService()

        setRegisterParams(userName, email, name, password)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doRegister(
            token,
            RequestBody.create(MediaType.parse("application/json"), gson.toJson(request4Register))
        ).enqueue(object : retrofit2.Callback<Response4Register> {
            override fun onFailure(call: Call<Response4Register>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4Register>?,
                response: Response<Response4Register>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    //==================================================================================================================
    /**
     * Model Request 4 Facebook Register
     */
    //==================================================================================================================
    override fun doRegisterWithFB(
        token: String,
        iRequestListener: IRequestListener<Response4Login>
    ) {
        initApiService()

        setFBRegisterParam(token)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doLogin(
            token,
            RequestBody.create(MediaType.parse("application/json"), gson.toJson(request4FBLogin))
        ).enqueue(object : retrofit2.Callback<Response4Login> {
            override fun onFailure(call: Call<Response4Login>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4Login>?,
                response: Response<Response4Login>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

}