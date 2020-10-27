package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.food

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.food.ICategoryFeedFoodActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.IFoodActivityViewImpl
import retrofit2.Response

class CategoryFeedFoodActivityPresenterImpl(
    val iCategoryFeedFoodActivityModel: ICategoryFeedFoodActivityModel,
    val iFoodActivityViewImpl: IFoodActivityViewImpl
) : ICategoryFeedFoodActivityPresenter {

    override fun getCategoryFeedFoodDataNew(
        isProgressList: Boolean,
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        isFirstLoad: Boolean
    ) {
        if (isProgressList)
            iFoodActivityViewImpl.showLoading4List()
        else {
            if (isFirstLoad)
                iFoodActivityViewImpl.showLoading()
        }


        iCategoryFeedFoodActivityModel.getCategoryFeedFoodDataNew(
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iFoodActivityViewImpl.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iFoodActivityViewImpl.hideLoading()
                    }

                    iFoodActivityViewImpl.handleArticleCategoryFoodData(response.body() as Response4ArticlesFeed)
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iFoodActivityViewImpl.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iFoodActivityViewImpl.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iFoodActivityViewImpl.showError(errorObj)


                }

                override fun onFail(t: Throwable?) {
                    if (isProgressList)
                        iFoodActivityViewImpl.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iFoodActivityViewImpl.hideLoading()
                    }
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iFoodActivityViewImpl.showError(errorObj)
                }
            })
    }


}