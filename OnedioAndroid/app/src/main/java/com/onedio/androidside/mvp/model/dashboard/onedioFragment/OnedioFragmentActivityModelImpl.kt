package com.onedio.androidside.mvp.model.dashboard.onedioFragment

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

class OnedioFragmentActivityModelImpl:
    IOnedioFragmentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun getAllCategoryAsTree(iRequestListener: IRequestListener<Response4AllCategory>) {
        initApiService()

        iApiService.getAllCategoryAsTree().enqueue(object : retrofit2.Callback<Response4AllCategory>{
            override fun onFailure(call: Call<Response4AllCategory>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AllCategory>?,
                response: Response<Response4AllCategory>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }
}