package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreContent

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article

interface IMoreContentActivityModel {


    fun initApiService()

    fun filterSearchedContentByParams(searchedWord: String, page: Int, perPage: Int, date: String, sort: String?, iRequestListener: IRequestListener<Response4Article>)

}