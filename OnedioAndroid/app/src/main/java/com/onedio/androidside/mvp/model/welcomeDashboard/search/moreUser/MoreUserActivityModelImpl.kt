package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreUser

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch

class MoreUserActivityModelImpl:
    IMoreUserActivityModel {
    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceOnedio()
    }

    override fun filterSearchedTagByParams(
        searchedWord: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4UserSearch>
    ) {

        initApiService()

        iApiService.filterSearchedUserByParams(searchedWord, page, perPage)
            .enqueue(object : retrofit2.Callback<Response4UserSearch> {
                override fun onFailure(call: Call<Response4UserSearch>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4UserSearch>?,
                    response: Response<Response4UserSearch>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })


    }
}