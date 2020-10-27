package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreContent

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreContent.IMoreContentActivityModel
import com.onedio.androidside.mvp.view.dashboard.search.moreContent.IMoreContentActivityView
import retrofit2.Response

class MoreContentActivityPresenterImpl(
    private val iMoreContentActivityModel: IMoreContentActivityModel,
    private val iMoreContentActivityView: IMoreContentActivityView
) : IMoreContentActivityPresenter {

    override fun filterSearchedContentByParams(
        searchedWord: String,
        page: Int,
        perPage: Int,
        date: String,
        sort: String?
    ) {
        iMoreContentActivityView.showLoading()
        iMoreContentActivityModel.filterSearchedContentByParams(
            searchedWord,
            page,
            perPage,
            date,
            sort,
            object :
                IRequestListener<Response4Article> {
                override fun onSuccess(response: Response<Response4Article>) {
                    iMoreContentActivityView.hideLoading()
                    iMoreContentActivityView.handleFilteredSearchData(response.body() as Response4Article)
                }

                override fun onUnSuccess(response: Response<Response4Article>) {
                    iMoreContentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iMoreContentActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iMoreContentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iMoreContentActivityView.showError(errorObj)
                }
            })
    }


}