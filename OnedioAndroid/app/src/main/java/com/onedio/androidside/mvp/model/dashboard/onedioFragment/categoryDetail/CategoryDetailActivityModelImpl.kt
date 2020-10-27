package com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.responseModel.Response4CategoryDetailItems

class CategoryDetailActivityModelImpl:
    ICategoryDetailActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceV2()
    }

    override fun getCategoryItem(
        categoryId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4CategoryDetailItems>
    ) {

        initApiService()

        iApiService.getCategoryItem(categoryId, page, perPage).enqueue(object : retrofit2.Callback<Response4CategoryDetailItems>{
            override fun onFailure(call: Call<Response4CategoryDetailItems>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4CategoryDetailItems>?,
                response: Response<Response4CategoryDetailItems>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })


    }
}