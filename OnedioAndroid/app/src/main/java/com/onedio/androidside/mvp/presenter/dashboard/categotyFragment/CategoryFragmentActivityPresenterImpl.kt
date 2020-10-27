package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment

import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.ICategoryFragmentActivityModel
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.ICategoryFragmentActivityView

class CategoryFragmentActivityPresenterImpl(
    val iCategoryFragmentActivityModel: ICategoryFragmentActivityModel,
    val iCategoryFragmentActivityView: ICategoryFragmentActivityView
) : ICategoryFragmentActivityPresenter {

    override fun getAllCategory() {

        iCategoryFragmentActivityModel.getAllCategory(object :
            IRequestListener<Response4AllCategory> {
            override fun onSuccess(response: Response<Response4AllCategory>) {
                iCategoryFragmentActivityView.hideLoading()
                iCategoryFragmentActivityView.handleCategoryData(response.body() as Response4AllCategory)
            }

            override fun onUnSuccess(response: Response<Response4AllCategory>) {
                iCategoryFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iCategoryFragmentActivityView.showError("category", errorObj)
            }

            override fun onFail(t: Throwable?) {
                iCategoryFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iCategoryFragmentActivityView.showError("category", errorObj)
            }
        })
    }

    override fun getBadgeCategory() {
        iCategoryFragmentActivityView.showLoading()
        iCategoryFragmentActivityModel.getBadgeCategory(object :
            IRequestListener<Response4AllCategory> {
            override fun onSuccess(response: Response<Response4AllCategory>) {
                iCategoryFragmentActivityView.handleBadgeCategoryData(response.body() as Response4AllCategory)
            }

            override fun onUnSuccess(response: Response<Response4AllCategory>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iCategoryFragmentActivityView.showError("badge", errorObj)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iCategoryFragmentActivityView.showError("badge", errorObj)
            }
        })
    }

}