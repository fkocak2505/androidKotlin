package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.IMoreTagActivityModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.IMoreTagActivityView
import retrofit2.Response

class MoreTagActivityPresenterImpl(
    val iMoreTagActivityModel: IMoreTagActivityModel,
    val iMoreTagActivityView: IMoreTagActivityView
) : IMoreTagActivityPresenter {
    override fun filterSearchedTagByParams(
        searchedWord: String,
        page: Int,
        perPage: Int
    ) {
        iMoreTagActivityView.showLoading()
        iMoreTagActivityModel.filterSearchedTagByParams(
            searchedWord,
            page,
            perPage,
            object :
                IRequestListener<Response4Tags> {
                override fun onSuccess(response: Response<Response4Tags>) {
                    iMoreTagActivityView.hideLoading()
                    iMoreTagActivityView.handleFilteredSearchData(response.body() as Response4Tags)
                }

                override fun onUnSuccess(response: Response<Response4Tags>) {
                    iMoreTagActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iMoreTagActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iMoreTagActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iMoreTagActivityView.showError(errorObj)
                }
            })
    }
}