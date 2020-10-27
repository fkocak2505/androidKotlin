package com.onedio.androidside.mvp.presenter.loginAndRegister

import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.loginAndRegister.ILoginAndRegisterDashboardActivityModel
import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token
import com.onedio.androidside.mvp.view.loginAndRegister.ILoginAndRegisterDashboardActivityView

class LoginAndRegisterDashboardActivityPresenterImpl(
    val iLoginAndRegisterDashboardActivityModel: ILoginAndRegisterDashboardActivityModel,
    val iLoginAndRegisterDashboardActivityView: ILoginAndRegisterDashboardActivityView
) : ILoginAndRegisterDashboardActivityPresenter {

    //==================================================================================================================
    /**
     * Presenter of Token
     */
    //==================================================================================================================
    override fun getToken() {
        // Show Loading
        iLoginAndRegisterDashboardActivityModel.getToken(object :
            IRequestListener<Response4Token> {
            override fun onSuccess(response: Response<Response4Token>) {
                iLoginAndRegisterDashboardActivityView.handleTokenResponse(response.body() as Response4Token)
            }

            override fun onUnSuccess(response: Response<Response4Token>) {
                val a = 2
            }

            override fun onFail(t: Throwable?) {
                val a = 2
            }
        })
    }
}