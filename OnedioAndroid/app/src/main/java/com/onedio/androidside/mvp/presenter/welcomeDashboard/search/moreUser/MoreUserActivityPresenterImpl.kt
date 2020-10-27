package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreUser

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreUser.IMoreUserActivityModel
import com.onedio.androidside.mvp.view.dashboard.search.moreUser.IMoreUserActivityView
import retrofit2.Response

class MoreUserActivityPresenterImpl(
    val iMoreUserActivityModel: IMoreUserActivityModel,
    val iMoreUserActivityView: IMoreUserActivityView
) : IMoreUserActivityPresenter {
    override fun filterSearchedUserByParams(searchedWord: String, page: Int, perPage: Int) {
        iMoreUserActivityView.showLoading()
        iMoreUserActivityModel.filterSearchedTagByParams(searchedWord, page, perPage, object :
            IRequestListener<Response4UserSearch> {
            override fun onSuccess(response: Response<Response4UserSearch>) {
                iMoreUserActivityView.hideLoading()
                iMoreUserActivityView.handleFilteredSearchData(response.body() as Response4UserSearch)
            }

            override fun onUnSuccess(response: Response<Response4UserSearch>) {
                iMoreUserActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iMoreUserActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iMoreUserActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iMoreUserActivityView.showError(errorObj)
            }
        })
    }
}