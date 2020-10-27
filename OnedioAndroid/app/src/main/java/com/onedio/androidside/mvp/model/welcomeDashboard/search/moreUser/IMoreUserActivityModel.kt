package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreUser

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch

interface IMoreUserActivityModel {

    fun initApiService()

    fun filterSearchedTagByParams(searchedWord: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4UserSearch>)

}