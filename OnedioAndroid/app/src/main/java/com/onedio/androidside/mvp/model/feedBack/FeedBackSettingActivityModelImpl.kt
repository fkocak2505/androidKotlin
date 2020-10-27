package com.onedio.androidside.mvp.model.feedBack

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.feedBack.requestParam.FeedBackRequestParam
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class FeedBackSettingActivityModelImpl : IFeedBackSettingActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceFeedBack()
    }

    override fun sendFeedBack(
        feedBackReqParam: FeedBackRequestParam,
        iRequestListener: IRequestListener<String>
    ) {
        initApiServiceNew()

        iApiService.sendFeedBack(
            "YZ9eEGoYAT41ZaKWUZiBM3xmerC3VX1W9OLR2Ty4",
            RequestBody.create(MediaType.parse("application/json"), Gson().toJson(feedBackReqParam))
        ).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }
        })

    }


}