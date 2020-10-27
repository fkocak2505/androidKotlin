package com.onedio.androidside.mvp.model.feedBack

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.feedBack.requestParam.FeedBackRequestParam

interface IFeedBackSettingActivityModel {

    fun initApiServiceNew()

    fun sendFeedBack(feedBackReqParam: FeedBackRequestParam, iRequestListener: IRequestListener<String>)

}