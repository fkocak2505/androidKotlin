package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation

interface IMessageActivityConversationModel {

    fun initApiService()

    fun getConversationIDMessages(
        conversationID: String,
        iRequestListener: IRequestListener<Response4ConversationIDMessage>
    )

    fun sendMessage(
        message: String,
        conversationID: String,
        iRequestListener: IRequestListener<Response4SendMessage>
    )

    fun startConversation(iRequestListener: IRequestListener<Response4StartConversation>)

}