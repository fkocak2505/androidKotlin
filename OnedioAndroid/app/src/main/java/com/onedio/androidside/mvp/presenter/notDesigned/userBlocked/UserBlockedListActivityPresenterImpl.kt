package com.onedio.androidside.mvp.presenter.notDesigned.userBlocked

import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.IUserBlockedListActivityModel
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked.IUserBlockedListActivityView

class UserBlockedListActivityPresenterImpl(var iUserBlockedListActivityModel: IUserBlockedListActivityModel,
                                           var iUserBlockedListActivityView: IUserBlockedListActivityView
):
    IUserBlockedListActivityPresenter {


    override fun getUserBlockedList() {
        iUserBlockedListActivityView.showLoading()
        iUserBlockedListActivityModel.getUserBlockedListData(object:
            IRequestListener<Response4UserBlockedList> {
            override fun onSuccess(response: Response<Response4UserBlockedList>) {
                iUserBlockedListActivityView.hideLoading()
                iUserBlockedListActivityView.handleUserBlockedListData(response.body() as Response4UserBlockedList)
            }

            override fun onUnSuccess(response: Response<Response4UserBlockedList>) {
                iUserBlockedListActivityView.hideLoading()
            }

            override fun onFail(t: Throwable?) {
                iUserBlockedListActivityView.hideLoading()
            }
        })

    }
}