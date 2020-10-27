package com.onedio.androidside.mvp.presenter.dashboard.trendingFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.dashboard.trendingFragment.ITrendingFragmentActivityModel
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.ITrendingFragmentActivityView
import retrofit2.Response

class TrendingFragmentActivityPresenterImpl(
    val iTrendingFragmentActivityModel: ITrendingFragmentActivityModel,
    val iTrendingFragmentActivityView: ITrendingFragmentActivityView
) : ITrendingFragmentActivityPresenter {

    override fun getTrendDataNew(page: Int, perPage: Int, isFirst: Boolean) {
        if (isFirst)
            iTrendingFragmentActivityView.showLoading()
        iTrendingFragmentActivityModel.getTrendDataNew(page, perPage, object :
            IRequestListener<Response4ArticlesFeed> {
            override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                if (isFirst)
                    iTrendingFragmentActivityView.hideLoading()
                iTrendingFragmentActivityView.handleTrendDataNew(response.body() as Response4ArticlesFeed)

            }

            override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                if (isFirst)
                    iTrendingFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iTrendingFragmentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                if (isFirst)
                    iTrendingFragmentActivityView.hideLoading()
            }
        })
    }

    override fun getTrendData(page: Int, perPage: Int) {
        iTrendingFragmentActivityView.showLoading()
        iTrendingFragmentActivityModel.getTrendData(page, perPage, object :
            IRequestListener<Response4FeedModel> {
            override fun onSuccess(response: Response<Response4FeedModel>) {
                iTrendingFragmentActivityView.hideLoading()
                //iTrendingFragmentActivityView.handleTrendData(response.body() as Response4FeedModel)
            }

            override fun onUnSuccess(response: Response<Response4FeedModel>) {
                iTrendingFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iTrendingFragmentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iTrendingFragmentActivityView.hideLoading()
            }
        })
    }


}