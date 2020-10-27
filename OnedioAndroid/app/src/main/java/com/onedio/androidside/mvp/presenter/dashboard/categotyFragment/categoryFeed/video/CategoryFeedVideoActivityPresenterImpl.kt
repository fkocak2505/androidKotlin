package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.video

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.video.ICategoryFeedVideoActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.IVideoActivityView
import retrofit2.Response

class CategoryFeedVideoActivityPresenterImpl(
    val iCategoryFeedVideoActivityModel: ICategoryFeedVideoActivityModel,
    val iVideoActivityView: IVideoActivityView
) : ICategoryFeedVideoActivityPresenter {

    override fun getVideoArticleDataNew(
        isProgressList: Boolean,
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        isFirstLoad: Boolean
    ) {
        if (isProgressList)
            iVideoActivityView.showLoading4List()
        else {
            if (isFirstLoad)
                iVideoActivityView.showLoading()
        }


        iCategoryFeedVideoActivityModel.getVideoArticleDataNew(
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iVideoActivityView.hideLoading4List()
                    else{
                        if (isFirstLoad)
                            iVideoActivityView.hideLoading()
                    }

                    iVideoActivityView.handleVideoDataNew(response.body() as Response4ArticlesFeed)
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iVideoActivityView.hideLoading4List()
                    else{
                        if (isFirstLoad)
                            iVideoActivityView.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iVideoActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    if (isProgressList)
                        iVideoActivityView.hideLoading4List()
                    else{
                        if (isFirstLoad)
                            iVideoActivityView.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iVideoActivityView.showError(errorObj)

                }
            })
    }


}