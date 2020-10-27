package com.onedio.androidside.mvp.presenter.dashboard.profileDetail.changePassword

import com.google.gson.Gson
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.IChangePasswordActivityModel
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.changePassword.IChangePasswordActivityView

class ChangePasswordActivityPresenterImpl(
    var iChangePasswordActivityModel: IChangePasswordActivityModel,
    var iChangePasswordActivityView: IChangePasswordActivityView
) : IChangePasswordActivityPresenter {

    override fun changeUserPassword(userId: String, email: String,userName: String, newPassword: String, oldPassword: String) {
        iChangePasswordActivityView.showLoading()
        iChangePasswordActivityModel.changeUserPassword(userId, email, userName, newPassword,oldPassword, object :
            IRequestListener<Response4ChangePassword> {
            override fun onSuccess(response: Response<Response4ChangePassword>) {
                iChangePasswordActivityView.hideLoading()
                iChangePasswordActivityView.handleChangePasswordAPIResult(response.body() as Response4ChangePassword)
            }

            override fun onUnSuccess(response: Response<Response4ChangePassword>) {
                iChangePasswordActivityView.hideLoading()
                var errorObj = response.errorBody().string()
                iChangePasswordActivityView.showErrorMessage(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                iChangePasswordActivityView.hideLoading()
            }
        })
    }

}