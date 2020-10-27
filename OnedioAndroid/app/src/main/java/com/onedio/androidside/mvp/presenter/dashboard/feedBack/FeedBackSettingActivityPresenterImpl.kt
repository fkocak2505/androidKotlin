package com.onedio.androidside.mvp.presenter.dashboard.feedBack

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.feedBack.IFeedBackSettingActivityModel
import com.onedio.androidside.mvp.model.feedBack.requestParam.FeedBackRequestParam
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.feedbackSetting.IFeedBackSetting
import retrofit2.Response

class FeedBackSettingActivityPresenterImpl(
    val iFeedBackSettingActivityModel: IFeedBackSettingActivityModel,
    val iFeedBackSetting: IFeedBackSetting
) : IFeedBackSettingActivityPresenter {
    override fun sendFeedBack(feedBackRequestParam: FeedBackRequestParam) {
        iFeedBackSetting.showLoading()
        iFeedBackSettingActivityModel.sendFeedBack(
            feedBackRequestParam,
            object : IRequestListener<String> {
                override fun onSuccess(response: Response<String>) {
                    iFeedBackSetting.hideLoading()
                    iFeedBackSetting.handleFeedBackResponse(response.body() as String)
                }

                override fun onUnSuccess(response: Response<String>) {
                    iFeedBackSetting.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iFeedBackSetting.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iFeedBackSetting.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message = "Lütfen internet bağlantınızı kontrol ediniz.."
                    iFeedBackSetting.showError(errorObj)
                }
            })
    }


}