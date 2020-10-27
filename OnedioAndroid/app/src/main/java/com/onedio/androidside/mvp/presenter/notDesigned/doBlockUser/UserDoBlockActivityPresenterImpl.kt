package com.onedio.androidside.mvp.presenter.notDesigned.doBlockUser

import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.IUserDoBlockActivityModel
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.view.notDesigned.doBlockUser.IUserDoBlockActivityView

class UserDoBlockActivityPresenterImpl(
    var iUserDoBlockActivityModel: IUserDoBlockActivityModel,
    var iUserDoBlockActivityView: IUserDoBlockActivityView
) : IUserDoBlockActivityPresenter {


    override fun doBlockUser(userName: String) {
        iUserDoBlockActivityView.showLoading()
        iUserDoBlockActivityModel.doBlockUser(userName,object :
            IRequestListener<Response4DoBlockedUser> {
            override fun onSuccess(response: Response<Response4DoBlockedUser>) {
                iUserDoBlockActivityView.hideLoading()
                iUserDoBlockActivityView.handleDoUserBlockedInfoData(response.body() as Response4DoBlockedUser)
            }

            override fun onUnSuccess(response: Response<Response4DoBlockedUser>) {
                iUserDoBlockActivityView.hideLoading()
            }

            override fun onFail(t: Throwable?) {
                iUserDoBlockActivityView.hideLoading()
            }
        })
    }
}