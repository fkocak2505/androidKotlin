package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags

interface IMoreTagActivityModel {

    fun initApiService()

    fun filterSearchedTagByParams(searchedWord: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4Tags>)

}