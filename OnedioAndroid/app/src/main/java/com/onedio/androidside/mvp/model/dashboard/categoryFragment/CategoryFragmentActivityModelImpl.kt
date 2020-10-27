package com.onedio.androidside.mvp.model.dashboard.categoryFragment

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

class CategoryFragmentActivityModelImpl:
    ICategoryFragmentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getAllCategory(iRequestListener: IRequestListener<Response4AllCategory>) {

        initApiServiceNew()

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

    override fun getBadgeCategory(iRequestListener: IRequestListener<Response4AllCategory>) {

        initApiServiceNew()

        iApiService.getBadgeCategory().enqueue(object : retrofit2.Callback<Response4AllCategory>{
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