package com.onedio.androidside.mvp.presenter.welcomeDashboard.search

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.welcomeDashboard.search.ISearchActivityModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.Response4MostSharedArticle
import com.onedio.androidside.mvp.view.dashboard.search.ISearchAcitivityView
import retrofit2.Response

class SearchActivityViewPresenterImpl(
    val iSearchActivityView: ISearchAcitivityView,
    private val iSearchActivityModel: ISearchActivityModel
) : ISearchActivityViewPresenter {

    override fun getMostPopularArticle() {
        iSearchActivityView.showLoading()
        iSearchActivityModel.getMostSharedArticleNew(object :
            IRequestListener<Response4ArticlesFeed> {
            override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                iSearchActivityView.hideLoading()
                iSearchActivityView.handleMostPopularArticle(response.body() as Response4ArticlesFeed)
            }

            override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                iSearchActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iSearchActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iSearchActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iSearchActivityView.showError(errorObj)
            }
        })
    }

    override fun getMostSharedArticle() {
        iSearchActivityView.showLoading()
        iSearchActivityModel.getMostSharedArticle(object :
            IRequestListener<Response4MostSharedArticle> {
            override fun onSuccess(response: Response<Response4MostSharedArticle>) {
                iSearchActivityView.hideLoading()
                //iSearchActivityView.handleMostSharedArticle(response.body() as Response4MostSharedArticle)
            }

            override fun onUnSuccess(response: Response<Response4MostSharedArticle>) {
                iSearchActivityView.hideLoading()
                var errorObj = response.errorBody().string()
                iSearchActivityView.showError(
                    Gson().fromJson(
                        errorObj,
                        Response4ErrorObj::class.java
                    )
                )
            }

            override fun onFail(t: Throwable?) {
                iSearchActivityView.hideLoading()
            }
        })
    }

    override fun getSearchData(type: String, keyword: String, page: Int, perPage: Int) {
        iSearchActivityView.showLoading()
        iSearchActivityModel.getSearchData(type, keyword, page, perPage, object :
            IRequestListener<String> {
            override fun onSuccess(response: Response<String>) {
                iSearchActivityView.hideLoading()

                when (type) {
                    "user" -> iSearchActivityView.handleUserSearchData(
                        Gson().fromJson(
                            response.body(),
                            Response4UserSearch::class.java
                        )
                    )
                    "tag" -> iSearchActivityView.handleTagSearchData(
                        Gson().fromJson(
                            response.body(),
                            Response4Tags::class.java
                        )
                    )
                    "article" -> iSearchActivityView.handleArticleSearchData(
                        Gson().fromJson(
                            response.body(),
                            Response4Article::class.java
                        )
                    )
                }

            }

            override fun onUnSuccess(response: Response<String>) {
                iSearchActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iSearchActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iSearchActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iSearchActivityView.showError(errorObj)
            }
        })
    }

}