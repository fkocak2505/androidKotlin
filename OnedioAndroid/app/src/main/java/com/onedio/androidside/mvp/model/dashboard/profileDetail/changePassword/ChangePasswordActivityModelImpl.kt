package com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.requestModel.ChangePasswordParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword
import com.onedio.androidside.singleton.OnedioSingletonPattern
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class ChangePasswordActivityModelImpl :
    IChangePasswordActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    private lateinit var changePasswordParams: ChangePasswordParams

    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun setNewUserPasswordParams(
        userId: String,
        email: String,
        userName: String,
        newPassword: String,
        oldPassword: String
    ) {
        gson = Gson()

        /*val userProfileInfo = Gson().fromJson(
            OnedioSingletonPattern.instance.getUserProfileInfoData(),
            Response4UsersProfile::class.java
        )*/

        changePasswordParams =
            ChangePasswordParams()
        //changePasswordParams.id = OnedioSingletonPattern.instance.getUserId()
        changePasswordParams.id = userId
        changePasswordParams.email = email
        changePasswordParams.username = userName
        changePasswordParams.password = newPassword
        changePasswordParams.oldPassword = oldPassword

    }

    override fun changeUserPassword(
        userId: String,
        email: String,
        userName: String,
        newPassword: String,
        oldPassword: String,
        iRequestListener: IRequestListener<Response4ChangePassword>
    ) {
        initApiService()

        setNewUserPasswordParams(userId, email, userName, newPassword, oldPassword)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.changePassword(
            token,
            "Bearer $accessToken",
            RequestBody.create(
                MediaType.parse("application/json"),
                gson.toJson(changePasswordParams)
            )
        ).enqueue(object : retrofit2.Callback<Response4ChangePassword> {
            override fun onFailure(call: Call<Response4ChangePassword>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4ChangePassword>?,
                response: Response<Response4ChangePassword>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }
}