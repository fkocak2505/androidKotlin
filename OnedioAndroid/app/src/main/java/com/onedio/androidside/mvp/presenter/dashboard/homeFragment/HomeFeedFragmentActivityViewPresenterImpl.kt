package com.onedio.androidside.mvp.presenter.dashboard.homeFragment

import android.view.View
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.IHomeFeedFragmentActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetDataWithTagId.Response4WidgetDataWithTagId
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.homeFragment.IHomeFeedFragmentActivityView
import retrofit2.Response

class HomeFeedFragmentActivityViewPresenterImpl(
    private val iHomeFeedFragmentActivityModel: IHomeFeedFragmentActivityModel,
    val iHomeFeedFragmentActivityView: IHomeFeedFragmentActivityView
) : IHomeFeedFragmentActivityViewPresenter {

    override fun getWidgetConfig() {

        iHomeFeedFragmentActivityModel.getWidgetConfig(object :
            IRequestListener<Response4WidgetConfig> {
            override fun onSuccess(response: Response<Response4WidgetConfig>) {
                iHomeFeedFragmentActivityView.handleWidgetConfigData(response.body() as Response4WidgetConfig)
            }

            override fun onUnSuccess(response: Response<Response4WidgetConfig>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }
        })
    }

    override fun getArticleSliderData() {

        iHomeFeedFragmentActivityModel.getSliderData(object :
            IRequestListener<Response4ArticleSlider> {
            override fun onSuccess(response: Response<Response4ArticleSlider>) {
                iHomeFeedFragmentActivityView.handleArticleSliderData(response.body() as Response4ArticleSlider)
            }

            override fun onUnSuccess(response: Response<Response4ArticleSlider>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iHomeFeedFragmentActivityView.showError(errorObj, true)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }
        })
    }

    override fun getArticlesFeed(
        response4WidgetConfig: Response4WidgetConfig,
        page: Int,
        perPage: Int
    ) {
        iHomeFeedFragmentActivityModel.getArticlesFeed(page, perPage, object :
            IRequestListener<Response4ArticlesFeed> {
            override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                iHomeFeedFragmentActivityView.handleArticlesFeedData(
                    response4WidgetConfig,
                    response.body() as Response4ArticlesFeed
                )
            }

            override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                var errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status!!.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"

                iHomeFeedFragmentActivityView.showError(errorObj, false)

            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }
        })
    }


    override fun getWidgetDataWithTagId(wName: String, targetId: String, page: Int, perPage: Int) {
        //iHomeFeedFragmentActivityView.showLoading()
        iHomeFeedFragmentActivityModel.getWidgetDataWithTagId(
            targetId,
            page,
            perPage,
            object :
                IRequestListener<Response4WidgetDataWithTagId> {
                override fun onSuccess(response: Response<Response4WidgetDataWithTagId>) {
                    //iHomeFeedFragmentActivityView.hideLoading()
                    /*iHomeFeedFragmentActivityView.handleWidgetDataWithTargetId(
                        wName,
                        response.body() as Response4WidgetDataWithTagId
                    )*/
                }

                override fun onUnSuccess(response: Response<Response4WidgetDataWithTagId>) {
                    //iHomeFeedFragmentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }

                override fun onFail(t: Throwable?) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }
            })
    }

    override fun getArticleFeedCategoryWidgetDataWidthTag4OnedioButton(
        wName: String,
        targetId: String,
        page: Int,
        perPage: Int
    ) {

        iHomeFeedFragmentActivityModel.getArticleFeedCategoryWidgetDataWidthTagId(
            targetId,
            page,
            perPage,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    iHomeFeedFragmentActivityView.handleOnedioButtonData(
                        wName,
                        response.body() as Response4ArticlesFeed
                    )
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }

                override fun onFail(t: Throwable?) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }
            })


    }

    override fun getArticleFeedCategoryWidgetDataWidthTagId(
        wName: String,
        targetId: String,
        page: Int,
        perPage: Int
    ) {
        iHomeFeedFragmentActivityModel.getArticleFeedCategoryWidgetDataWidthTagId(
            targetId,
            page,
            perPage,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    iHomeFeedFragmentActivityView.handleArticleFeedCategoryWidgetDataWithTagId(
                        wName,
                        response.body() as Response4ArticlesFeed
                    )
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }

                override fun onFail(t: Throwable?) {

                }
            })
    }

    override fun getArticleFeedCategoryWidgetDataWithCategoryId(
        wName: String,
        categoryId: String,
        page: Int,
        perPage: Int
    ) {
        iHomeFeedFragmentActivityModel.getArticleFeedCategoryWidgetDataWithCategoryId(
            categoryId,
            page,
            perPage,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    iHomeFeedFragmentActivityView.handleArticleFeedCategoryWidgetDataWithCategoryId(
                        wName,
                        response.body() as Response4ArticlesFeed
                    )
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }

                override fun onFail(t: Throwable?) {

                }
            })
    }

    override fun getCategoryWidgetDataByName(
        isFirst: Boolean,
        wName: String,
        targetId: String,
        page: Int,
        perPage: Int
    ) {
        //iHomeFeedFragmentActivityView.showLoading()
        iHomeFeedFragmentActivityModel.getCategoryWidgetDataByName(targetId, page, perPage, object :
            IRequestListener<Response4FeedModel> {
            override fun onSuccess(response: Response<Response4FeedModel>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                iHomeFeedFragmentActivityView.handleCategoryDataByName(
                    isFirst,
                    response.body() as Response4FeedModel
                )
            }

            override fun onUnSuccess(response: Response<Response4FeedModel>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }
        })
    }

    override fun addFavorite(legacyId: Long, viewText: View, viewIcon: View) {
        //iHomeFeedFragmentActivityView.showLoading()
        iHomeFeedFragmentActivityModel.addFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                iHomeFeedFragmentActivityView.handleAddFavoriteDataModel(
                    response.body() as Response4AddFavoriteArticle,
                    viewText,
                    viewIcon,
                    legacyId
                )
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }

            override fun onFail(t: Throwable?) {
                //iHomeFeedFragmentActivityView.hideLoading()
            }
        })
    }

    override fun deleteFavorite(legacyId: Long, viewText: View, viewIcon: View) {
        //iHomeFeedFragmentActivityView.showLoading()
        iHomeFeedFragmentActivityModel.deleteFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                iHomeFeedFragmentActivityView.handleDeleteFavoriteDataModel(
                    response.body() as Response4AddFavoriteArticle,
                    viewText,
                    viewIcon,
                    legacyId
                )
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                //iHomeFeedFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iHomeFeedFragmentActivityView.showError(errorObj, false)
            }

            override fun onFail(t: Throwable?) {
                //iHomeFeedFragmentActivityView.hideLoading()
            }
        })
    }

    override fun getArticleData4Infinite(page: Int, perPage: Int, isProgressShow: Boolean) {
        if (isProgressShow)
            iHomeFeedFragmentActivityView.showLoading()

        iHomeFeedFragmentActivityModel.getArticlesFeed(
            page,
            perPage,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressShow)
                        iHomeFeedFragmentActivityView.hideLoading()
                    iHomeFeedFragmentActivityView.handleArticleFeedData4Infinite(
                        response.body() as Response4ArticlesFeed
                    )
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressShow)
                        iHomeFeedFragmentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }

                override fun onFail(t: Throwable?) {
                    if (isProgressShow)
                        iHomeFeedFragmentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iHomeFeedFragmentActivityView.showError(errorObj, false)
                }
            })
    }

}