package com.onedio.androidside.mvp.model.dashboard.categoryFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

interface ICategoryFragmentActivityModel {

    fun initApiServiceNew()

    fun getAllCategory(iRequestListener: IRequestListener<Response4AllCategory>)

    fun getBadgeCategory(iRequestListener: IRequestListener<Response4AllCategory>)

}