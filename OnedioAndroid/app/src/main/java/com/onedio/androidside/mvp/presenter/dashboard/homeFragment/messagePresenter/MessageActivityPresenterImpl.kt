package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter

import com.google.gson.Gson
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.IMessageActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.IMessageActivityView

class MessageActivityPresenterImpl(
    val iMessageActivityModel: IMessageActivityModel,
    val iMessageFragmentActivityView: IMessageActivityView
) : IMessageActivityPresenter {

    override fun getUserAllConversation() {
        iMessageFragmentActivityView.showLoading()
        iMessageActivityModel.getUserAllConversation(object :
            IRequestListener<Response4UserAllConversation> {
            override fun onSuccess(response: Response<Response4UserAllConversation>) {
                iMessageFragmentActivityView.hideLoading()
                iMessageFragmentActivityView.handleUserAllConversationData(response.body() as Response4UserAllConversation)
            }

            override fun onUnSuccess(response: Response<Response4UserAllConversation>) {
                iMessageFragmentActivityView.hideLoading()
                val errorObj = response.errorBody().string()
                iMessageFragmentActivityView.showError(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                /// TO DO
            }
        })
    }

    override fun deleteConversation(deletingConversationID: String) {
        iMessageFragmentActivityView.showLoading()
        iMessageActivityModel.deleteConversation(deletingConversationID, object:
            IRequestListener<Response4DeleteConversation> {
            override fun onSuccess(response: Response<Response4DeleteConversation>) {
                iMessageFragmentActivityView.hideLoading()
                iMessageFragmentActivityView.handleDeleteConversation(response.body() as Response4DeleteConversation)
            }

            override fun onUnSuccess(response: Response<Response4DeleteConversation>) {
                iMessageFragmentActivityView.hideLoading()
                val errorObj = response.errorBody().string()
                iMessageFragmentActivityView.showError(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                /// TO DO
            }
        })
    }
}