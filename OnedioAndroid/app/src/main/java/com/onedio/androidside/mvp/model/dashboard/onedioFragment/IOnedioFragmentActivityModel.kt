package com.onedio.androidside.mvp.model.dashboard.onedioFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

interface IOnedioFragmentActivityModel {

    fun initApiService()

    fun getAllCategoryAsTree(iRequestListener: IRequestListener<Response4AllCategory>)

}