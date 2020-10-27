package com.onedio.androidside.mvp.presenter.loginAndRegister.login.forgotPassword

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.IForgotPasswordActivityModel
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword
import com.onedio.androidside.mvp.view.loginAndRegister.login.forgotPassword.IForgotPasswordActivityView
import retrofit2.Response

class ForgotPasswordActivityPresenterImpl(
    var iForgotPasswordActivityModel: IForgotPasswordActivityModel,
    var iForgotPasswordActivityView: IForgotPasswordActivityView
) : IForgotPasswordActivityPresenter {


    override fun sendEmailForForgotPassword(email: String) {
        iForgotPasswordActivityView.showLoading()
        iForgotPasswordActivityModel.sendEmail4ForgotPassword(email, object :
            IRequestListener<Response4ForgotPassword> {
            override fun onSuccess(response: Response<Response4ForgotPassword>) {
                iForgotPasswordActivityView.hideLoading()
                iForgotPasswordActivityView.handleResponseData(response.body() as Response4ForgotPassword)
            }

            override fun onUnSuccess(response: Response<Response4ForgotPassword>) {
                iForgotPasswordActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iForgotPasswordActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iForgotPasswordActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iForgotPasswordActivityView.showError(errorObj)
            }
        })
    }
}