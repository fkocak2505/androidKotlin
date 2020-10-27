package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag.tagsArticle

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.tagsArticle.ITagsArticleActivityModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.ITagsArticleActivityView
import retrofit2.Response

class TagsArticleActivityPresenterImpl(
    val iTagsArticleActivityModel: ITagsArticleActivityModel,
    val iTagsArticleActivityView: ITagsArticleActivityView
) : ITagsArticleActivityPresenter {
    override fun getTagArticle(
        isProgressList: Boolean,
        tagId: String, page: Int, perPage: Int, dateFilterType: String,
        sortType: String,
        isFirstLoad: Boolean
    ) {

        if (isProgressList)
            iTagsArticleActivityView.showLoading4List()
        else {
            if (isFirstLoad)
                iTagsArticleActivityView.showLoading()
        }


        iTagsArticleActivityModel.getTagsArticle(
            tagId,
            page,
            perPage,
            dateFilterType,
            sortType,
            object :
                IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    if (isProgressList)
                        iTagsArticleActivityView.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iTagsArticleActivityView.hideLoading()
                    }

                    iTagsArticleActivityView.handleTagsArticle(response.body() as Response4ArticlesFeed)
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {

                    if (isProgressList)
                        iTagsArticleActivityView.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iTagsArticleActivityView.hideLoading()
                    }

                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iTagsArticleActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {

                    if (isProgressList)
                        iTagsArticleActivityView.hideLoading4List()
                    else {
                        if (isFirstLoad)
                            iTagsArticleActivityView.hideLoading()
                    }

                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu an teknik bir arıza mevcut. Daha sonra tekrar deneyin.."
                    iTagsArticleActivityView.showError(errorObj)
                }
            })

    }
}