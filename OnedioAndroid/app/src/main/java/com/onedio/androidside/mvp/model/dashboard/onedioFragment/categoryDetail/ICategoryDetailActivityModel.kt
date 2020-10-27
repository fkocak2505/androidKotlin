package com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.responseModel.Response4CategoryDetailItems

interface ICategoryDetailActivityModel {

    fun initApiService()

    fun getCategoryItem(categoryId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4CategoryDetailItems>)

}