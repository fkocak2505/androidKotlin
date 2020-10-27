package com.onedio.androidside.mvp.presenter.loginAndRegister.login

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.loginAndRegister.login.ILoginFragmentActivityModel
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.view.loginAndRegister.login.ILoginFragmentActivityView
import retrofit2.Response

class LoginFragmentActivityPresenterImpl(
    val iLoginFragmentActivityModel: ILoginFragmentActivityModel,
    val iLoginFragmentActivityView: ILoginFragmentActivityView
) : ILoginFragmentActivityPresenter {

    //==================================================================================================================
    /**
     * Presenter of Login
     */
    //==================================================================================================================
    override fun doLogin(email: String, password: String) {
        iLoginFragmentActivityView.showLoading()
        iLoginFragmentActivityModel.doLogin(email, password, object :
            IRequestListener<Response4Login> {
            override fun onSuccess(response: Response<Response4Login>) {
                iLoginFragmentActivityView.hideLoading()
                iLoginFragmentActivityView.handleSuccessLoginResponse(response.body() as Response4Login)
            }

            override fun onUnSuccess(response: Response<Response4Login>) {
                iLoginFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message = "Kullanıcı adı ve ya şifre hatalı"
                iLoginFragmentActivityView.showError(errorObj)

            }

            override fun onFail(t: Throwable?) {
                iLoginFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iLoginFragmentActivityView.showError(errorObj)
            }
        })
    }

    //==================================================================================================================
    /**
     * Presenter of FB Login
     */
    //==================================================================================================================
    override fun doLoginWithFB(token: String) {
        iLoginFragmentActivityView.showLoading()
        iLoginFragmentActivityModel.doLoginWithFB(token, object :
            IRequestListener<Response4Login> {
            override fun onSuccess(response: Response<Response4Login>) {
                iLoginFragmentActivityView.hideLoading()
                iLoginFragmentActivityView.handleSuccessLoginResponse(response.body() as Response4Login)
            }

            override fun onUnSuccess(response: Response<Response4Login>) {
                iLoginFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iLoginFragmentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iLoginFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iLoginFragmentActivityView.showError(errorObj)
            }
        })
    }
}