package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter.conversation

import com.google.gson.Gson
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.IMessageActivityConversationModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.IMessageActivityConversationView

class MessageActivityConversationPresenterImpl(
    val iMessageActivityConversationModel: IMessageActivityConversationModel,
    val iMessageActivityConversationView: IMessageActivityConversationView
) : IMessageActivityConversationPresenter {

    override fun getConversationIDMessages(conversationID: String) {
        iMessageActivityConversationView.showLoading()
        iMessageActivityConversationModel.getConversationIDMessages(conversationID,object :
            IRequestListener<Response4ConversationIDMessage> {
            override fun onSuccess(response: Response<Response4ConversationIDMessage>) {
                iMessageActivityConversationView.hideLoading()
                iMessageActivityConversationView.handleConversationIDMessages(response.body() as Response4ConversationIDMessage)
            }

            override fun onUnSuccess(response: Response<Response4ConversationIDMessage>) {
                /// TO DO
                iMessageActivityConversationView.hideLoading()
                var errorObj = response.errorBody().string()
                iMessageActivityConversationView.showError(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                /// TO DO
            }
        })
    }


    override fun sendMessage(message: String, conversationID :String) {
        iMessageActivityConversationView.showLoading()
        iMessageActivityConversationModel.sendMessage(message, conversationID, object :
            IRequestListener<Response4SendMessage> {
            override fun onSuccess(response: Response<Response4SendMessage>) {
                iMessageActivityConversationView.hideLoading()
                iMessageActivityConversationView.handleSendMessageData(response.body() as Response4SendMessage)
            }

            override fun onUnSuccess(response: Response<Response4SendMessage>) {
                iMessageActivityConversationView.hideLoading()
                var errorObj = response.errorBody().string()
                iMessageActivityConversationView.showError(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun startConversation() {
        iMessageActivityConversationView.showLoading()
        iMessageActivityConversationModel.startConversation(object :
            IRequestListener<Response4StartConversation> {
            override fun onSuccess(response: Response<Response4StartConversation>) {
                iMessageActivityConversationView.hideLoading()
                iMessageActivityConversationView.handleStartConversationData(response.body() as Response4StartConversation)
            }

            override fun onUnSuccess(response: Response<Response4StartConversation>) {
                iMessageActivityConversationView.hideLoading()
                val errorObj = response.errorBody().string()
                iMessageActivityConversationView.showError(Gson().fromJson(errorObj, Response4ErrorObj::class.java))
            }

            override fun onFail(t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}