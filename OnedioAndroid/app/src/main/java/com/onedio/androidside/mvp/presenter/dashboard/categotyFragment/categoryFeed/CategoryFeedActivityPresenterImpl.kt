package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.ICategoryFeedActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.ICategoryFeedActivityView
import retrofit2.Response

class CategoryFeedActivityPresenterImpl(
    val iCategoryFeedActivityModel: ICategoryFeedActivityModel,
    val iCategoryFeedActivityView: ICategoryFeedActivityView
) : ICategoryFeedActivityPresenter {

    override fun getArticleFeedByCategoryIdNew(
        isProgressList: Boolean,
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        isFirstLoad: Boolean
    ) {
        if (isProgressList)
            iCategoryFeedActivityView.showLoading4List()
        else{
            if(isFirstLoad)
            iCategoryFeedActivityView.showLoading()
        }


        iCategoryFeedActivityModel.getArticleFeedByCategoryIdNew(
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iCategoryFeedActivityView.hideLoading4List()
                    else{
                        if(isFirstLoad)
                            iCategoryFeedActivityView.hideLoading()
                    }
                    iCategoryFeedActivityView.handleArticleFeedDataByCategoryIdNew(response.body() as Response4ArticlesFeed)
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iCategoryFeedActivityView.hideLoading4List()
                    else{
                        if(isFirstLoad)
                            iCategoryFeedActivityView.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iCategoryFeedActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    if (isProgressList)
                        iCategoryFeedActivityView.hideLoading4List()
                    else{
                        if(isFirstLoad)
                            iCategoryFeedActivityView.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iCategoryFeedActivityView.showError(errorObj)
                }
            })
    }
}