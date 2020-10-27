package com.onedio.androidside.mvp.presenter.dashboard.feedBack

import com.onedio.androidside.mvp.model.feedBack.requestParam.FeedBackRequestParam

interface IFeedBackSettingActivityPresenter {

    fun sendFeedBack(feedBackRequestParam: FeedBackRequestParam)

}