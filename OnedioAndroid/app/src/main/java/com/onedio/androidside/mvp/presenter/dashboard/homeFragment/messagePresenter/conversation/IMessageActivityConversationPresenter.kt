package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter.conversation

interface IMessageActivityConversationPresenter {

    fun startConversation()

    fun getConversationIDMessages(conversationID: String)

    fun sendMessage(message: String, conversationID: String)

}