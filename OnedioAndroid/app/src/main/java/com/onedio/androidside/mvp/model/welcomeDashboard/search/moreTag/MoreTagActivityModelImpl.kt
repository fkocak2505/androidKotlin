package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags

class MoreTagActivityModelImpl :
    IMoreTagActivityModel {


    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceOnedio()
    }

    override fun filterSearchedTagByParams(
        searchedWord: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4Tags>
    ) {

        initApiService()

        iApiService.filterSearchedTagByParams(searchedWord, page, perPage)
            .enqueue(object : retrofit2.Callback<Response4Tags> {
                override fun onFailure(call: Call<Response4Tags>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4Tags>?,
                    response: Response<Response4Tags>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })


    }
}