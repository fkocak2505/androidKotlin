package com.onedio.androidside.mvp.presenter.loginAndRegister.register

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.model.loginAndRegister.register.IRegisterFragmentActivityModel
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register
import com.onedio.androidside.mvp.view.loginAndRegister.register.IRegisterFragmentAcitivityView
import retrofit2.Response

class RegisterFragmentAcitivityPresenterImpl(
    val iRegisterFragmentActivityModel: IRegisterFragmentActivityModel,
    val iRegisterFragmentAcitivityView: IRegisterFragmentAcitivityView
) : IRegisterFragmentAcitivityPresenter {


    override fun isMailExist(email: String) {
        iRegisterFragmentAcitivityView.showLoading()
        iRegisterFragmentActivityModel.isMailExist(email, object :
            IRequestListener<Response4IsMailExist> {
            override fun onSuccess(response: Response<Response4IsMailExist>) {
                iRegisterFragmentAcitivityView.hideLoading()
                iRegisterFragmentAcitivityView.handleCheckMail(response.body() as Response4IsMailExist)
            }

            override fun onUnSuccess(response: Response<Response4IsMailExist>) {
                iRegisterFragmentAcitivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iRegisterFragmentAcitivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun isUserNameExist(userName: String) {
        iRegisterFragmentAcitivityView.showLoading()
        iRegisterFragmentActivityModel.isUserNameExist(userName, object :
            IRequestListener<Response4IsMailExist> {
            override fun onSuccess(response: Response<Response4IsMailExist>) {
                iRegisterFragmentAcitivityView.hideLoading()
                iRegisterFragmentAcitivityView.handleCheckUserName(response.body() as Response4IsMailExist)
            }

            override fun onUnSuccess(response: Response<Response4IsMailExist>) {
                iRegisterFragmentAcitivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iRegisterFragmentAcitivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    //==================================================================================================================
    /**
     * Presenter Of Register
     */
    //==================================================================================================================
    override fun doRegister(userName: String, email: String, name: String, password: String) {
        iRegisterFragmentAcitivityView.showLoading()
        iRegisterFragmentActivityModel.doRegister(
            userName,
            email,
            name,
            password,
            object :
                IRequestListener<Response4Register> {
                override fun onSuccess(response: Response<Response4Register>) {
                    iRegisterFragmentAcitivityView.hideLoading()
                    iRegisterFragmentAcitivityView.handleSuccessRegisterResponse(response.body() as Response4Register)
                }

                override fun onUnSuccess(response: Response<Response4Register>) {
                    var errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status!!.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"

                    iRegisterFragmentAcitivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    var errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status!!.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"

                    iRegisterFragmentAcitivityView.showError(errorObj)
                }
            })
    }

    //==================================================================================================================
    /**
     * Presenter Of Facebook Register
     */
    //==================================================================================================================
    override fun doRegisterWithFB(token: String) {
        iRegisterFragmentAcitivityView.showLoading()
        iRegisterFragmentActivityModel.doRegisterWithFB(token, object :
            IRequestListener<Response4Login> {
            override fun onSuccess(response: Response<Response4Login>) {
                iRegisterFragmentAcitivityView.hideLoading()
                //iRegisterFragmentAcitivityView.handleSuccessRegisterResponseWithFB(response.body() as Response4Login)
            }

            override fun onUnSuccess(response: Response<Response4Login>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }


}