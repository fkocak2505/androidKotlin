package com.onedio.androidside.mvp.presenter.dashboard.onedioFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.IOnedioFragmentActivityModel
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.onedioFragment.IOnedioFragmentActivityView
import retrofit2.Response

class OnedioFragmentActivityPresenterImpl(
    var iOnedioFragmentActivityModel: IOnedioFragmentActivityModel,
    var iOnedioFragmentActivityView: IOnedioFragmentActivityView
) : IOnedioFragmentActivityPresenter {
    override fun getAllCategoryAsTree() {
        iOnedioFragmentActivityView.showLoading()
        iOnedioFragmentActivityModel.getAllCategoryAsTree(object :
            IRequestListener<Response4AllCategory> {
            override fun onSuccess(response: Response<Response4AllCategory>) {
                iOnedioFragmentActivityView.hideLoading()
                iOnedioFragmentActivityView.handleAllCategoryData(response.body() as Response4AllCategory)
            }

            override fun onUnSuccess(response: Response<Response4AllCategory>) {
                iOnedioFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iOnedioFragmentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iOnedioFragmentActivityView.hideLoading()
            }
        })
    }
}